package br.com.ivan.news.presentation;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class ArticlePresentation {
    private String source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime publishedAt;
    private String content;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        if (content == null || content.isBlank()) {
            return "Para saber mais acesse a url";
        }
        return content.split("\\[\\+")[0];
    }

    public void setContent(String content) {
        this.content = content;
    }
}
