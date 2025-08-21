package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BasketService {

    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }
    /**
     * добавляем товар в корзину по его UUID
     */
    public void addProductBasket(UUID id) {
        Product product = storageService.getProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Продукт с таким ID не найден"));

        productBasket.addProduct(id);
    }
    /**
     * возвращает состояние корзины пользователя с полным описанием товаров и общей стоимостью
     */
    public UserBasket getUserBasket() {
        /**
         * получаем мапу товаров из компонента корзины (UUID -> количество)
         */
        Map <UUID, Integer> productsInBasket = productBasket.getProducts();
/**
 * // Преобразуем мапу в список BasketItem: для каждого id ищем Product
 * и формируем BasketItem с количеством
 */
        List<BasketItem> items = productBasket.getProducts().entrySet().stream()
                .map(entry -> {
                    /**
                     * Получаем объект Product по id из StorageService, выбрасываем исключение, если не найден
                     */
                    Product product = storageService.getProductById(entry.getKey())
                            .orElseThrow(() -> new IllegalArgumentException("Продукт с таким ID не найден"));
                    return new BasketItem(product,entry.getValue());
                })
                .collect(Collectors.toList());
        return new UserBasket(items);
    }



}
