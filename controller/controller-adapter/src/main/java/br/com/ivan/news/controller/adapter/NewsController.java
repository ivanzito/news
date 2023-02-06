package br.com.ivan.news.controller.adapter;

import br.com.ivan.news.controller.port.HeadlinesController;
import br.com.ivan.news.presentation.ArticlePresentation;
import br.com.ivan.news.usecase.port.Category;
import br.com.ivan.news.usecase.port.SearchHeadlines;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Objects;

@RestController
@RequestMapping("/news")
public class NewsController implements HeadlinesController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private final SearchHeadlines searchHeadlines;

    public NewsController(SearchHeadlines searchHeadlines) {
        this.searchHeadlines = searchHeadlines;
    }

    @Override
    //@CategoryConstraint
    @GetMapping(path = "/headlines", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<ArticlePresentation> getHeadLines(String keyword, String category) {
        logger.info("Looking for keyword:{} and category: {}", keyword, category);

        if (Objects.isNull(category)) {
            return searchHeadlines.getArticles(keyword, null);
        }
        return searchHeadlines.getArticles(keyword, Category.of(category));
    }
}
