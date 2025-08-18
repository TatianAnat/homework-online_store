package org.skypro.skyshop.model.service;

import org.springframework.stereotype.Service;

/**
 * с помощью аннотации @Service указываем Spring зарегистрировать этот класс как бин сервиса
 */
@Service
public class SearchService {

    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }
}
