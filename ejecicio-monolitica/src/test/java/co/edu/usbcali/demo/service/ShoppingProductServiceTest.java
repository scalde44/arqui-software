package co.edu.usbcali.demo.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import co.edu.usbcali.demo.domain.ShoppingProduct;

@SpringBootTest
@Rollback(false)
class ShoppingProductServiceTest {
	private final static Logger log = LoggerFactory.getLogger(ShoppingProductServiceTest.class);
	@Autowired
	ShoppingProductService shoppingProductService;

	@Test
	void total() {
		// Arrange
		Long total = 0l;
		Integer carId = 10;
		// Act
		total = shoppingProductService.totalShoppingProductByShoppingCart(carId);

		// Assert

		assertTrue(total > 0);
	}

	@Test
	void items() {
		// Arrange
		Integer items = 0;
		Integer carId = 9;
		// Act
		items = shoppingProductService.totalItems(carId);

		// Assert

		assertTrue(items > 0);
	}

	@Test
	void shpr() {
		// Arrange
		Integer carId = 9;
		String proId = "APPL45";
		ShoppingProduct shoppingProduct = null;
		// Act
		shoppingProduct = shoppingProductService.getShprByCarPro(carId, proId);
		// Assert
		assertTrue(shoppingProduct != null);
	}

	@Test
	void shprIdByCarId() {
		// Arrange
		Integer carId = 9;
		List<Integer> lista = null;
		// Act
		lista = shoppingProductService.getShprIdByCarId(carId);
		lista.forEach(shpr -> {
			log.info("Id: " + shpr);
		});
		// Assert
		assertTrue(lista.size() > 0);
	}

}