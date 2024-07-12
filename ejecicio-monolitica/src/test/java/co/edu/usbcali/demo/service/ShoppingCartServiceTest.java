package co.edu.usbcali.demo.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import co.edu.usbcali.demo.domain.Customer;
import co.edu.usbcali.demo.domain.PaymentMethod;
import co.edu.usbcali.demo.domain.Product;
import co.edu.usbcali.demo.domain.ShoppingCart;

@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class ShoppingCartServiceTest {

	private final static Logger log = LoggerFactory.getLogger(ShoppingCartServiceTest.class);

	private static Integer carId = null;

	private static String email = "abaglowbn@furl.net";

	private static Integer payId = 1;

	@Autowired
	ShoppingCartService shoppingCartService;

	@Autowired
	CustomerService customerService;

	@Autowired
	PaymentMethodService paymentMethodService;

	@Test
	@Order(1)
	void save() throws Exception {
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setCarId(carId);
		shoppingCart.setItems(5);
		shoppingCart.setTotal(83424L);
		shoppingCart.setEnable("Y");

		Optional<Customer> customerOptional = customerService.findById(email);
		assertTrue(customerOptional.isPresent(), "El customer con email " + email + " no existe");

		Customer customer = customerOptional.get();
		shoppingCart.setCustomer(customer);

		Optional<PaymentMethod> paymentMethodOptional = paymentMethodService.findById(payId);
		assertTrue(paymentMethodOptional.isPresent(), "El payment method con id " + payId + " no existe");

		PaymentMethod paymentMethod = paymentMethodOptional.get();
		shoppingCart.setPaymentMethod(paymentMethod);

		shoppingCart = shoppingCartService.save(shoppingCart);

		carId = shoppingCart.getCarId();

		assertNotNull(carId, "El carId es nulo");

	}

	@Test
	@Order(2)
	void findById() throws Exception {
		Optional<ShoppingCart> shoppingCartOptional = shoppingCartService.findById(carId);
		assertTrue(shoppingCartOptional.isPresent(), "El shoppingCartOpcional con carId " + carId + " no existe");
	}

	@Test
	@Order(3)
	void update() throws Exception {
		Optional<ShoppingCart> shoppingCartOptional = shoppingCartService.findById(carId);
		assertTrue(shoppingCartOptional.isPresent(), "El shoppingCartOpcional con carId " + carId + " no existe");

		ShoppingCart shoppingCart = shoppingCartOptional.get();
		shoppingCart.setEnable("Y");

		shoppingCartService.update(shoppingCart);
	}

	@Test
	@Order(4)
	void delete() throws Exception {
		Optional<ShoppingCart> shoppingCartOptional = shoppingCartService.findById(carId);
		assertTrue(shoppingCartOptional.isPresent(), "El shoppingCartOpcional con carId " + carId + " no existe");

		ShoppingCart shoppingCart = shoppingCartOptional.get();
		shoppingCartService.delete(shoppingCart);
		log.info("delete");
	}

	@Test
	void getProductosByCarId() throws Exception {
		List<Product> products = null;
		Integer carId = 23;
		products = shoppingCartService.findProductsByCarId(carId);
		if (products != null) {
			products.forEach(p -> {
				log.info(""+p.getName());
			});
		}

		assertNotNull(products, "lista vacía");
	}

}
