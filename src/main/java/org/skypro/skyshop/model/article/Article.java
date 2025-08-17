package org.skypro.skyshop.model.article;

import org.skypro.skyshop.model.search.Searchable;

public class Article implements Searchable {
    private String title;

    public Article(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Название:  " + title;
    }

    @Override
    public String getSearchTerm() {
        return title;
    }

    @Override
    public String getName() {
        return title;
    }
}
