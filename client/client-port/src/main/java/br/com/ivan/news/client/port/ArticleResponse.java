package br.com.ivan.news.client.port;

import com.fasterxml.jackson.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class ArticleResponse implements Comparable<ArticleResponse> {
    private SourceResponse source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime publishedAt;
    private String content;

    public SourceResponse getSource() {
        return source;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "ArticleResponse{" +
                "source=" + source +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                ", publishedAt=" + publishedAt +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public int compareTo(ArticleResponse articleResponse) {
        return articleResponse.publishedAt.compareTo(this.publishedAt);
    }
}
