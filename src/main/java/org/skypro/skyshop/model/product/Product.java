package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;
import java.util.UUID;

public abstract class Product implements Searchable {
    private final UUID id;
    private String name;
    private int price;

    /**
     * Основной конструктор с UUID id, названием и ценой
      */
    public Product(UUID id, String name, int price) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название продукта не может быть пустым, состоять только из пробелов или быть null.");
        }
        if (id == null) {
            throw new IllegalArgumentException("ID не может быть null.");
        }
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{id=" + id + ", name='" + name + "', price=" + price + "}";
    }

    public boolean isSpecial() {
        return true;
    }

    @Override
    @JsonIgnore
    public String getSearchTerm() {
        return name;
    }

    @JsonIgnore
    public String getContentType() {
        return "Product";
    }

}
