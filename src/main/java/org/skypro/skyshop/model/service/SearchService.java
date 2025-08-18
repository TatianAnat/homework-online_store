package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.model.search.SearchResult;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * с помощью аннотации @Service указываем Spring зарегистрировать этот класс как бин сервиса
 */
@Service
public class SearchService {

    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    /**
     * поиск по строке, возвращает коллекцию SearchResult
     * @param query поисковая строка
     * @return коллекция результатов поиска
     */
    public Collection<SearchResult> search(String query) {
        if (query == null || query.isBlank()) {
            return java.util.Collections.emptyList();
        }

        String queryLower = query.toLowerCase();

        return storageService.getAllSearchable().stream()
                .filter(item -> item.getName() != null &&
                        item.getName().toLowerCase().contains(queryLower))
                .map(SearchResult::fromSearchable)
                .collect(Collectors.toList());
    }
}
