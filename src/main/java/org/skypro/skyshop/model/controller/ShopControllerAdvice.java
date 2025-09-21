package org.skypro.skyshop.model.controller;

import org.skypro.skyshop.model.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.exceptions.ShopError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * обрабатываем исключения во всех контроллерах.
 */
@ControllerAdvice
public class ShopControllerAdvice {
    /**
     * Метод handleNoSuchProductException ловит NoSuchProductException
     * и формирует объект ShopError с кодом ошибки "PRODUCT_NOT_FOUND"
     * и сообщением из исключения (если оно пустое — подставляет дефолт).
     * return Возвращается ResponseEntity с HTTP статусом 404 (NOT_FOUND) и телом в виде объекта ShopError, который будет сериализован в JSON.
     */
    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError> handleNoSuchProductException(NoSuchProductException ex) {
        ShopError error = new ShopError(
                "PRODUCT_NOT_FOUND",
                ex.getMessage() != null ? ex.getMessage() : "Продукт не найден");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}