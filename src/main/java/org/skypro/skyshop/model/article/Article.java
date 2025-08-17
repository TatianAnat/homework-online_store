package org.skypro.skyshop.model.article;

import org.skypro.skyshop.model.search.Searchable;

import java.util.UUID;

public class Article implements Searchable {

    private final UUID id;
    private String title;

    public Article(UUID id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Название:  " + title + ", ID: " + id;
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
