package org.skypro.skyshop.model.product;

import org.skypro.skyshop.model.search.Searchable;

import java.util.UUID;

public abstract class Product implements Searchable {
    private String name;
    private int price;
    private final UUID id;

    public Product(UUID id, String name, int price) {
        /**
         * Метод isBlank() используется для проверки, является ли строка пустой или нет. Пустая строка или строка, содержащая только пробелы, считается пустой.
         */
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название продукта не может быть пустым, состоять только из пробелов или быть null.");
        }
        if (id == null){
            throw new IllegalArgumentException("ID не может быть null.");
        }
        this.name = name;
        this.price = price;
        this.id = id;
    }
//    public Product(String name) {
//    }
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
        return "Product{ id= " + id + "наименование = " + name + ": " + " стоимость = " + price + '}';
    }

    public boolean isSpecial() {
        return true;
    }

    @Override
    public String getSearchTerm() {
        return name;
    }
}
