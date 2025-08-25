package org.skypro.skyshop.model.basket;

import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@Component
@SessionScope
public class ProductBasket {
    private final Map<UUID, Integer> products = new HashMap<>();

    /**
     * добавление продукта по его UUID
     * если товар есть, то увеличиваем количество на 1
     * merge - увеличивает счётчик по ключу или ставит 1, если товара в корзине нет
     */
    public void addProduct(UUID id) {
        products.merge(id, 1, Integer::sum);
    }

    public Map<UUID, Integer> getProducts() {
        return Collections.unmodifiableMap(products);
    }
}
