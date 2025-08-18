package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    public String getName() {
        return title;
    }

    @Override
    @JsonIgnore
    public String getSearchTerm() {
        return title;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Название: " + title + ", ID: " + id;
    }

    @JsonIgnore
    public String getContentType() {
        return "Article";
    }
}
