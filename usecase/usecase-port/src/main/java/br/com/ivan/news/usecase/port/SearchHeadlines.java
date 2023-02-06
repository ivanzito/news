package br.com.ivan.news.usecase.port;

import br.com.ivan.news.presentation.ArticlePresentation;
import reactor.core.publisher.Flux;

public interface SearchHeadlines {

    Flux<ArticlePresentation> getArticles(String keyword, Category categories);
    
}
