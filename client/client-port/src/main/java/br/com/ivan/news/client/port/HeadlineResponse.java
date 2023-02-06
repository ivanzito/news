package br.com.ivan.news.client.port;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HeadlineResponse {
    private List<ArticleResponse> articles;

    public List<ArticleResponse> getArticles() {
        return articles;
    }
}
