package org.skypro.skyshop.utill;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.service.StorageService;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.service.BasketService;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BasketServiceTest {
	private StorageService storageServiceMock;
	private ProductBasket productBasketMock;
	private BasketService basketService;

	@BeforeEach
	public void setup() {
		storageServiceMock = mock(StorageService.class);
		productBasketMock = mock(ProductBasket.class);
		basketService = new BasketService(productBasketMock, storageServiceMock);
	}
	/**
	 * Добавление несуществующего товара в корзину вызывает исключение
 	 */
	@Test
	public void addNonExistentProductThrowsException() {
		UUID fakeId = UUID.randomUUID();
		when(storageServiceMock.getProductById(fakeId)).thenReturn(Optional.empty());

		assertThrows(RuntimeException.class, () -> basketService.addProductToBasket(fakeId));
	}
	/**
	 * Добавление существующего товара вызывает метод addProduct у productBasket
	 */
	@Test
	public void addExistingProductCallsAddProduct() {
		UUID productId = UUID.randomUUID();
		Product product = mock(Product.class);
		when(storageServiceMock.getProductById(productId)).thenReturn(Optional.of(product));

		basketService.addProductToBasket(productId);

		verify(productBasketMock, times(1)).addProduct(productId);
	}

	/**
	 * Метод getUserBasket возвращает пустую корзину если productBasket пуст
	 */
	@Test
	public void getUserBasketReturnsEmptyIfNoProducts() {
		when(productBasketMock.getProducts()).thenReturn(Collections.emptyMap());

		var basket = basketService.getUserBasket();

		assertTrue(basket.isEmpty());
	}

	/**
	 * Метод getUserBasket возвращает корзину с продуктами, если они есть
	 */
	@Test
	public void getUserBasketReturnsProductsIfPresent() {
		UUID productId = UUID.randomUUID();
		when(productBasketMock.getProducts()).thenReturn(Map.of(productId, 2));
		Product product = mock(Product.class);
		when(storageServiceMock.getProductById(productId)).thenReturn(Optional.of(product));
		// Допустим, basketService.getUserBasket возвращает Map<Product, Integer>

		var basket = basketService.getUserBasket();

		assertFalse(basket.isEmpty());
		assertTrue(basket.containsKey(product));
		assertEquals(2, basket.get(product));
	}
}
