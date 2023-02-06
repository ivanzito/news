package br.com.ivan.news.database.port;

import reactor.core.publisher.Mono;

import java.util.List;

public interface ArticleControlRepository {

    <S extends ArticleControlProxy> Mono<S> findByKey(String key);
    void save(String key, List<ArticleEntity> articles);

}
