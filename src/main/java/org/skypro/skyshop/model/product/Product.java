package org.skypro.skyshop.model.product;

public class Product {
    private String name;
    int price;

    public Product(String name, int price) {
        /**
         * Метод isBlank() используется для проверки, является ли строка пустой или нет. Пустая строка или строка, содержащая только пробелы, считается пустой.
         */
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название продукта не может быть пустым, состоять только из пробелов или быть null.");
        }
        this.name = name;
        this.price = price;
    }

    public Product(String name) {
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{ " + name + ": " + " стоимость = " + price + '}';
    }

    public boolean isSpecial() {
        return true;
    }
}
