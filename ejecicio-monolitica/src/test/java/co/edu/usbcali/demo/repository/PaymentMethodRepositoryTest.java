package co.edu.usbcali.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.PaymentMethod;

@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class PaymentMethodRepositoryTest {
	
	private final static Logger log=LoggerFactory.getLogger(PaymentMethodRepositoryTest.class);
	
	private static Integer payId=null;
	
	@Autowired
	PaymentMethodRepository paymentMethodRepository;
	
	@Test
	@Order(1)
	@Transactional
	void save() {
		PaymentMethod paymentMethod=new PaymentMethod();
		paymentMethod.setEnable("Y");
		paymentMethod.setName("EFECTY");
		
		paymentMethod=paymentMethodRepository.save(paymentMethod);
		payId=paymentMethod.getPayId();
		
		assertNotNull(payId,"El PayId es nulo");
		
		log.info("PayId"+payId);
	}

	@Test
	@Order(2)
	@Transactional
	void findById() {
		assertTrue(paymentMethodRepository.findById(payId).isPresent());
		PaymentMethod paymentMethod=paymentMethodRepository.findById(payId).get();
		
		assertNotNull(paymentMethod,"El paymentMethod no existe");
	}
	
	@Test
	@Order(3)
	@Transactional
	void update() {
		assertTrue(paymentMethodRepository.findById(payId).isPresent());
		PaymentMethod paymentMethod=paymentMethodRepository.findById(payId).get();
		paymentMethod.setEnable("N");
		
		paymentMethodRepository.save(paymentMethod);
	}
	
	@Test
	@Order(4)
	@Transactional
	void delete() {
		assertTrue(paymentMethodRepository.findById(payId).isPresent());
		PaymentMethod paymentMethod=paymentMethodRepository.findById(payId).get();
		
		paymentMethodRepository.delete(paymentMethod);
	}
}
