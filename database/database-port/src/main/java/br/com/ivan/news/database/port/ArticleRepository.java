package br.com.ivan.news.database.port;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ArticleRepository {

    Flux<ArticleEntity> saveAll(Iterable<ArticleEntity> entities);

    Flux<ArticleEntity> findByKey(String key);
    Mono<ArticleEntity> save(ArticleEntity entity);

}
