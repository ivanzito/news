package br.com.ivan.news.database.adapter;

import br.com.ivan.news.database.adapter.entity.ArticleProxy;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ArticleRepositoryImpl extends ReactiveMongoRepository<ArticleProxy, String> {

    @Override
    <S extends ArticleProxy> Flux<S> saveAll(Iterable<S> entities);

    @Override
    <S extends ArticleProxy> Mono<S> findOne(Example<S> example);

    @Override
    <S extends ArticleProxy> Mono<S> save(S entity);

    @Query(value = "{'article.key': ?0}")
    Flux<ArticleProxy> findByArticle(String key);

}
