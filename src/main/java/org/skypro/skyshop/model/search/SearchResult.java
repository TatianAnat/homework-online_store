package org.skypro.skyshop.model.search;

public final class SearchResult {
    /**
     * final - запрет наследования
     */
    private final String id;
    private final String name;
    private final String contentType;
    private final Searchable searchable;

    private SearchResult(String id, String name, String contentType, Searchable searchable) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
        this.searchable = searchable;
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
     * создаём SearchResult из Searchable. Id должен быть не пустой
     */
    public static SearchResult fromSearchable(Searchable item) {
        return new SearchResult(item.getId().toString(), item.getName(),item.getContentType(),item);
    }

    public Searchable getSearchable() {
        return searchable;
    }
}
