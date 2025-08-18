package org.skypro.skyshop.model.search;

public class SearchResult {
    /**
     * final - запрет наследования
     */
    private final String id;
    private final String name;
    private final String contentType;

    public SearchResult(String id, String name, String contentType) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContentType() {
        return contentType;
    }

    /**
     * создаём SearchResult из Searchable
     */
    public static SearchResult fromSearchable(Searchable item) {
        String id = item.getId() != null ? item.getId().toString() : null;
        String name = item.getName();

        String contentType = item.getClass().getSimpleName();
        return new SearchResult(id,name,contentType);
    }
}
