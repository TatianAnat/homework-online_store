package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {

    private final Map<UUID, Product> productStorage;
    private final Map<UUID, Article> articleStorage;

    public StorageService() {
        productStorage = new HashMap<>();
        articleStorage = new HashMap<>();
        initTestData();
    }
    /**
     * возвращает коллекцию всехстатей
     */
    public Collection<Article> getAllArticles(){
        return Collections.unmodifiableCollection(articleStorage.values());
    }
    /**
     * возвращает коллекцию всех продуктов
     */
    public Collection<Product> getAllProducts() {
        return Collections.unmodifiableCollection(productStorage.values());
    }
    /**
     * приватный метод инициализации тестовых данных
     */
    private void initTestData() {
        Article a1 = new Article(UUID.randomUUID(), "лазерный принтер");
        Article a2 = new Article(UUID.randomUUID(), "сенсорный монитор");
        Article a3 = new Article(UUID.randomUUID(), "беспроводная клавиатура");

        articleStorage.put(a1.getId(),a1);
        articleStorage.put(a2.getId(),a2);
        articleStorage.put(a3.getId(),a3);

        Product p1 = new SimpleProduct(UUID.randomUUID(),"Принтер Canon", 15000);
        Product p2 = new DiscountedProduct(UUID.randomUUID(),"Монитор Samsung", 20000, 10);
        Product p3 = new SimpleProduct(UUID.randomUUID(),"Клавиатура Logitech", 5000);

        productStorage.put(p1.getId(), p1);
        productStorage.put(p2.getId(), p2);
        productStorage.put(p3.getId(), p3);

    }

}
