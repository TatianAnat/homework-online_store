package org.skypro.skyshop.utill;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.model.service.SearchService;
import org.skypro.skyshop.model.service.StorageService;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SearchServiceTest {

	private StorageService storageServiceMock;
	private SearchService searchService;

	@BeforeEach
	public  void setUp() {
		storageServiceMock = mock(StorageService.class);
		searchService = new SearchService(storageServiceMock);
	}
	/**
	поиск при отсутствии объектов в StorageService
	 */
	@Test
	public void testSearch_NoObjectsInStorage_ReturnsEmpty{

		when(storageServiceMock.getAllSearchable()).thenReturn(Collections.emptyList());

		Searchable[] results = searchService.search("тест");
		assertNotNull(results);
		assertEquals(0,countNonNull(results));
	}
	/**
	поиск, когда объекты есть, но подходящих нет
	 */
	@Test
	public void testSearch_ObjectsExistButNoMatch_ReturnsEmpty{
		List<Searchable> objects = List.of(
				new SimpleProduct(UUID.randomUUID(),"Телефон",10000),
				new Article(UUID.randomUUID(),"Газета")
		);
		when(storageServiceMock.getAllSearchable()).thenReturn(objects);

		Searchable[] results = searchService.search("ноутбук");
		assertNotNull(results);
		assertEquals(0,countNonNull(results));
	}
	/**
	 * поиск с подходящими объектами
	 */
	@Test
	public void testSearch_MatchingObjects_ReturnsThem() {
		Article article = new Article(UUID.randomUUID(), "Смартфон с хорошей камерой");
		SimpleProduct product = new SimpleProduct(UUID.randomUUID(),"Смартфон Samsung", 20000);
		List<Searchable> objects = List.of(article,product);
		when(storageServiceMock.getAllSearchable().thenReturn(objects);

		Searchable[] results = searchService.search("Смартфон");
		assertNotNull(results);

		assertTrue(containsSearchTerm(results,"Смартфон"));
		assertTrue(results.length <= 5);
	}
	/**
	 * Вспомогательные методы
	 */
	private int countNonNull(Searchable[] arr) {
		int count = 0;
		for (Searchable s : arr) {
			if (s != null) {
				count++;
			}
		}
		return  count;
	}

	private boolean containsSearchTerm(Searchable[] arr, String term) {
		for (Searchable s : arr) {
			if (s != null && s.getSearchTerm().contains(term)) {
				return true;
			}
		}
		return false;
	}

}
