package org.skypro.skyshop.model.basket;

import org.skypro.skyshop.model.product.Product;

import java.util.List;

public final class UserBasket {

    private final List<BasketItem> items;
    private final int total;

    public UserBasket(List<BasketItem> items) {
        this.items = List.copyOf(items);
        this.total = calculateTotal(items);
    }

    private int calculateTotal(List<BasketItem> items) {
        return items.stream()
                .mapToInt(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public int getTotal() {
        return total;
    }

    /**
     * Корзина считается пустой, если нет элементов или все имеют количество 0
     */
    public boolean isEmpty() {
        return items.isEmpty() || items.stream().allMatch(item -> item.getQuantity() == 0);
    }

    /**
     * проверка, есть ли товар в корзине
     */
    public boolean containsKey(Product product) {
        return items.stream()
                .anyMatch(item -> item.getProduct().equals(product));
    }
    /**
     *получение количества товара в корзине по объекту Product
     * если товар не найден, возвращает 0
     */
    public int get(Product product) {
        return items.stream()
                .filter(item -> item.getProduct().equals(product))
                .map(BasketItem::getQuantity)
                .findFirst()
                .orElse(0);
    }
}