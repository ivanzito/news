package br.com.ivan.news.controller.port;

import java.util.List;

public class HeadlineInput {
    String keyword;
    List<String> categories;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
