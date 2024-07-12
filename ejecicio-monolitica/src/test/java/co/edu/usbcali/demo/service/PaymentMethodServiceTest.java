package co.edu.usbcali.demo.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

import co.edu.usbcali.demo.domain.PaymentMethod;

@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class PaymentMethodServiceTest {

	private final static Logger log = LoggerFactory.getLogger(PaymentMethodServiceTest.class);

	private static Integer payId = null;

	@Autowired
	PaymentMethodService paymentMethodService;

	@Test
	@Order(1)
	void save() throws Exception {
		PaymentMethod paymentMethod = new PaymentMethod();
		paymentMethod.setEnable("Y");
		paymentMethod.setName("DAVIVIENDA");

		paymentMethod = paymentMethodService.save(paymentMethod);
		payId = paymentMethod.getPayId();

		assertNotNull(payId, "El PayId es nulo");

		log.info("PayId: " + payId);
	}

	@Test
	@Order(2)
	void findById() throws Exception {
		log.info("findById");

		Optional<PaymentMethod> paymentMethodOptional = paymentMethodService.findById(payId);

		assertTrue(paymentMethodOptional.isPresent(), "El paymentMethod no existe");
	}

	@Test
	@Order(3)
	void update() throws Exception {
		log.info("update");

		Optional<PaymentMethod> paymentMethodOptional = paymentMethodService.findById(payId);

		assertTrue(paymentMethodOptional.isPresent(), "El producto no existe");

		PaymentMethod paymentMethod = paymentMethodOptional.get();
		paymentMethod.setEnable("N");

		paymentMethodService.update(paymentMethod);
	}

	@Test
	@Order(4)
	void delete() throws Exception {
		log.info("delete");

		Optional<PaymentMethod> paymentMethodOptional = paymentMethodService.findById(payId);

		assertTrue(paymentMethodOptional.isPresent(), "El producto no existe");

		PaymentMethod paymentMethod = paymentMethodOptional.get();

		paymentMethodService.delete(paymentMethod);

	}

}
