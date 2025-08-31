package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private  int discountPercent;
    private int basePrice;

    public DiscountedProduct(UUID id, String name, int basePrice, int discountPercent) {
        super (id, name, basePrice);
        if (basePrice < 0) {
            throw new IllegalArgumentException("Базовая цена не может быть отрицательной");
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Процент скидки должен быть от 0 до 100");
        }
        this.basePrice = basePrice;
        this.discountPercent = discountPercent;
    }
    @Override
    public int getPrice() {
        return basePrice - (basePrice * discountPercent / 100);
    }
    public int getDiscountPercent() {
        return discountPercent;
    }

    public int getBasePrice() {
        return basePrice;
    }
}
