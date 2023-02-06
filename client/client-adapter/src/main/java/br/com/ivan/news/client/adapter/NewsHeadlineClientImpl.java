package br.com.ivan.news.client.adapter;

import br.com.ivan.news.client.port.ArticleResponse;
import br.com.ivan.news.client.port.HeadlineResponse;
import br.com.ivan.news.client.port.NewsHeadlineClient;
import io.netty.channel.ChannelOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Flux;
import reactor.netty.http.client.HttpClient;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;


@Service
public class NewsHeadlineClientImpl implements NewsHeadlineClient {

    @Value("${news.api.key}")
    private String key;

    private final HttpClient client = HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 500)
            .responseTimeout(Duration.ofSeconds(1));
    private final WebClient webClient = WebClient
            .builder()
            .clientConnector(new ReactorClientHttpConnector(client))
            .build();
    private final String KEYWORD = "q";
    private final String COUNTRY = "country";
    private final String CATEGORY = "category";
    private final String API_KEY = "apiKey";
    private final String HOST = "newsapi.org";
    private final String PATH = "/v2/top-headlines";
    private final String SCHEME = "https";

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());


    public Flux<ArticleResponse> getHeadlinesByCategory(String keyword, List<String> categories) {
        logger.info("Looking for headlines [key:{}] and [category:{}]", keyword, categories);
        return webClient.get()
                .uri(uriBuilder -> uri(keyword, categories).apply(uriBuilder).build())
                .exchangeToFlux(clientResponse -> clientResponse.bodyToFlux(HeadlineResponse.class))
                .flatMapIterable(HeadlineResponse::getArticles)
                .retryWhen(Retry.backoff(3, Duration.ofSeconds(2)));
    }

    @Override
    public Flux<ArticleResponse> getHeadlines(String keyword) {

        logger.info("Looking for headlines [key:{}]", keyword);
        return webClient.get()
                .uri(uriBuilder -> uri(keyword).apply(uriBuilder).build())
                .exchangeToFlux(clientResponse -> clientResponse.bodyToFlux(HeadlineResponse.class))
                .flatMapIterable(HeadlineResponse::getArticles)
                .retryWhen(Retry.backoff(3, Duration.ofSeconds(2)));
    }

    private Function<UriBuilder, UriBuilder> uri(String keyword) {
        return uriBuilder -> uriBuilder
            .queryParam(KEYWORD,keyword)
            .queryParam(COUNTRY,"br")
            .queryParam(API_KEY,key)
            .host(HOST)
            .path(PATH)
            .scheme(SCHEME);
    }

    private Function<UriBuilder, UriBuilder> uri(String keyword, List<String> categories) {
        return uri(keyword).andThen(uriBuilder -> uriBuilder.queryParam(CATEGORY, categories));
    }

    @Override
    public Flux<ArticleResponse> getHeadlines() {
        return getHeadlines("*");
    }
}
