package br.com.ivan.news.usecase.adapter.mapper;


import br.com.ivan.news.client.port.ArticleResponse;
import br.com.ivan.news.database.port.ArticleEntity;
import br.com.ivan.news.presentation.ArticlePresentation;
import br.com.ivan.news.usecase.port.Article;
import br.com.ivan.news.usecase.port.Source;

import java.util.Objects;

public class Mapper {

    public static Article of(ArticlePresentation articlePresentation) {
        Article article = new Article();
        article.setPublishedAt(articlePresentation.getPublishedAt());
        article.setAuthor(articlePresentation.getAuthor());
        article.setContent(articlePresentation.getContent());
        article.setUrl(articlePresentation.getUrl());
        article.setDescription(articlePresentation.getDescription());
        article.setUrlToImage(articlePresentation.getUrlToImage());
        article.setTitle(articlePresentation.getTitle());
        Source source = new Source();
        source.setId(null);
        source.setName(articlePresentation.getSource());
        article.setSource(source);
        return article;
    }
    public static Article of(ArticleEntity articleEntity) {
        Article article = new Article();
        article.setKey(articleEntity.getKey());
        article.setPublishedAt(articleEntity.getPublishedAt());
        article.setAuthor(articleEntity.getAuthor());
        article.setContent(articleEntity.getContent());
        article.setUrl(articleEntity.getUrl());
        article.setDescription(articleEntity.getDescription());
        article.setUrlToImage(articleEntity.getUrlToImage());
        article.setTitle(articleEntity.getTitle());
        Source source = new Source();
        source.setId(null);
        source.setName(articleEntity.getSource());
        article.setSource(source);
        return article;
    }

    public static ArticlePresentation of(Article article) {
        ArticlePresentation presentation = new ArticlePresentation();
        presentation.setPublishedAt(article.getPublishedAt());
        presentation.setAuthor(article.getAuthor());
        presentation.setContent(article.getContent());
        presentation.setUrl(article.getUrl());
        presentation.setDescription(article.getDescription());
        presentation.setUrlToImage(article.getUrlToImage());
        presentation.setTitle(article.getTitle());
        presentation.setSource(article.getSource().getName());
        return presentation;
    }

    public static Article of(ArticleResponse response) {
        Article article = new Article();
        article.setPublishedAt(response.getPublishedAt());
        article.setAuthor(response.getAuthor());
        article.setContent(response.getContent());
        article.setUrl(response.getUrl());
        article.setDescription(response.getDescription());
        article.setUrlToImage(response.getUrlToImage());
        article.setTitle(response.getTitle());
        Source source = new Source(response.getSource().getId(), response.getSource().getName());
        article.setSource(source);
        return article;
    }

    public static ArticleEntity of(ArticleResponse article, String key) {
        ArticleEntity articleEntity = new ArticleEntity(key);
        if (Objects.nonNull(article)) {
            articleEntity.setDescription(article.getDescription());
            articleEntity.setContent(article.getContent());
            articleEntity.setAuthor(article.getAuthor());
            articleEntity.setPublishedAt(article.getPublishedAt());
            articleEntity.setTitle(article.getTitle());
            articleEntity.setUrl(article.getUrl());
            articleEntity.setUrlToImage(article.getUrlToImage());
            articleEntity.setSource(article.getSource().getName());
        }
        return articleEntity;
    }
}