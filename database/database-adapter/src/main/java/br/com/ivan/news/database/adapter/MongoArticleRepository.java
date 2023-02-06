package br.com.ivan.news.database.adapter;

import br.com.ivan.news.database.adapter.entity.ArticleProxy;
import br.com.ivan.news.database.port.ArticleEntity;
import br.com.ivan.news.database.port.ArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Component
public class MongoArticleRepository implements ArticleRepository {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());


    @Autowired
    private ArticleRepositoryImpl articleRepository;

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    @Override
    public Flux<ArticleEntity> saveAll(Iterable<ArticleEntity> entities) {
        Iterator<ArticleEntity> iterator = entities.iterator();
        List<ArticleProxy> articles = new ArrayList<>();
        while(iterator.hasNext()) {
            articles.add(new ArticleProxy(iterator.next()));
        }
        return articleRepository.saveAll(articles).map(ArticleProxy::getArticle);
    }

    @Override
    public Flux<ArticleEntity> findByKey(String key) {
        logger.info("Looking for: " + key);
        return articleRepository
                .findByArticle(key)
                .map(ArticleProxy::getArticle);
    }

    @Override
    public Mono<ArticleEntity> save(ArticleEntity entity) {
        logger.info("Saving: " + entity);
        return articleRepository.save(new ArticleProxy(entity))
                .map(ArticleProxy::getArticle);

    }
}
