package br.com.ivan.news.usecase.adapter;

import br.com.ivan.news.client.port.ArticleResponse;
import br.com.ivan.news.database.port.ArticleEntity;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

import java.time.LocalDateTime;


public class SearchHeadlinesUseCaseTest {

    @Test
    void returnArticlesFromDatabase() {
        TestPublisher<ArticleEntity> publisher = TestPublisher.<ArticleEntity>create()
                .next(articleDaoFactory());
        StepVerifier
                .create(publisher)
                .expectNextCount(1L)
                .verifyComplete();
    }

    @Test
    void returnArticlesFromSource() {
/*        StepVerifier
                .create(usecase.getArticles("*", null))
                .expectNext(TO_ARTICLE.apply(articleDaoFactory()))
                .verifyComplete();*/
    }

    private static ArticleResponse articleResponseFactory() {
        return new ArticleResponse();
    }

    private static ArticleEntity articleDaoFactory() {
        return new ArticleEntity() {
            @Override
            public String getKey() {
                return "key";
            }

            @Override
            public String getSource() {
                return null;
            }

            @Override
            public String getAuthor() {
                return null;
            }

            @Override
            public String getTitle() {
                return null;
            }

            @Override
            public String getDescription() {
                return null;
            }

            @Override
            public String getUrl() {
                return null;
            }

            @Override
            public String getUrlToImage() {
                return null;
            }

            @Override
            public LocalDateTime getPublishedAt() {
                return null;
            }

            @Override
            public String getContent() {
                return null;
            }
        };
    }
}
