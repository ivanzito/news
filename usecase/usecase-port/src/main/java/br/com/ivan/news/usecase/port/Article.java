package br.com.ivan.news.usecase.port;

import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Article implements Comparable<Article> {
    private Source source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime publishedAt;
    private String content;

    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = String.format("%s-%s", DateTimeFormatter.ofPattern("yyyy-MM-dd"), key);;
    }

    public Source getSource() {
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

    public void setSource(Source source) {
        this.source = source;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public void setPublishedAt(LocalDateTime publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "source='" + source + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public int compareTo(Article article) {
        return article.publishedAt.compareTo(this.publishedAt);
    }
}
