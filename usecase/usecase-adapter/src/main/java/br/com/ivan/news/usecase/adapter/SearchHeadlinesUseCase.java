package br.com.ivan.news.usecase.adapter;

import br.com.ivan.news.client.port.NewsHeadlineClient;
import br.com.ivan.news.database.port.ArticleEntity;
import br.com.ivan.news.database.port.ArticleRepository;
import br.com.ivan.news.presentation.ArticlePresentation;
import br.com.ivan.news.usecase.adapter.mapper.Mapper;
import br.com.ivan.news.usecase.port.Category;
import br.com.ivan.news.usecase.port.SearchHeadlines;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SearchHeadlinesUseCase implements SearchHeadlines {

    private final NewsHeadlineClient newsHeadlineClient;
    private final ArticleRepository articleRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());


    public SearchHeadlinesUseCase(final NewsHeadlineClient newsHeadlineClient,
                                  final ArticleRepository articleRepository) {
        this.newsHeadlineClient = newsHeadlineClient;
        this.articleRepository = articleRepository;
    }

    private final Function<List<Category>, List<String>> paramCategory = categories ->
            categories == null
                ? Collections.emptyList()
                : categories.stream().map(Category::name).collect(Collectors.toList());

    private final Function<List<Category>, String> paramCategoryString = categories ->
            String.join("", paramCategory.apply(categories));

    @Override
    public Flux<ArticlePresentation> getArticles(String keyword, Category category) {
        logger.info("[Use case] getting articles with keyword: {} and categories: {}", keyword, category);

        Hooks.onOperatorDebug();

        return articleRepository
                .findByKey(databaseKey(keyword, category))
                .map(Mapper::of)

            .switchIfEmpty(
                newsHeadlineClient
                .getHeadlinesByCategory(keyword, null)
                .doOnEach(signal -> {
                    if (signal.get() != null && signal.get().getDescription() != null) {
                        ArticleEntity articleDAO = Mapper.of(signal.get(), databaseKey(keyword, category));
                        articleRepository.save(articleDAO).subscribe();
                    }
                }).map(Mapper::of)
            )

            .map(Mapper::of);
    }

    private String databaseKey(String keyword, Category category) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String key = keyword == null ? "*" : keyword;
        return String.format("%s-%s-%s", formatter.format(LocalDate.now()), key, category);
    }
}
