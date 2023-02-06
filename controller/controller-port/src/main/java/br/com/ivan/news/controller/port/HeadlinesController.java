package br.com.ivan.news.controller.port;

import br.com.ivan.news.presentation.ArticlePresentation;
import reactor.core.publisher.Flux;

public interface HeadlinesController {

    Flux<ArticlePresentation> getHeadLines(String keyword, String category);
}
