package br.com.ivan.news.client.port;

import reactor.core.publisher.Flux;

import java.util.List;

public interface NewsHeadlineClient {
    //for now only pt-BR
    Flux<ArticleResponse> getHeadlinesByCategory(String keywords, List<String> categories);
    Flux<ArticleResponse> getHeadlines(String keywords);
    Flux<ArticleResponse> getHeadlines();
}
