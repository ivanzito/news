package br.com.ivan.news.database.adapter.entity;

import br.com.ivan.news.database.port.ArticleEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "article")
public class ArticleProxy extends ArticleEntity {

    public ArticleProxy() {}

    public ArticleProxy(ArticleEntity article) {
        this.article = article;
    }

    @Id
    private String id;

    private ArticleEntity article;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArticleEntity getArticle() {
        return article;
    }

    public void setArticle(ArticleEntity article) {
        this.article = article;
    }
}