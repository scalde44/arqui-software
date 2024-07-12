package co.edu.usbcali.demo.jpa;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.Customer;

@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class CustomerTest {

	private final static String email = "scalderon1504@gmail.com";
	private final static Logger log=LoggerFactory.getLogger(CustomerTest.class);
	
	@Autowired
	EntityManager entityManager;

	@Test
	@Transactional
	@Order(1)
	void save() {
		// Siga si no es nulo
		assertNotNull(entityManager, "EntityMager nulo");
		Customer customer = entityManager.find(Customer.class, email);

		// Siga si es nulo
		assertNull(customer, "El cliente con email " + email + " ya existe");
		
		customer = new Customer();
		customer.setAddress("Avenida siempre viva 123");
		customer.setEmail(email);
		customer.setEnable("Y");
		customer.setName("Steven Calderon");
		customer.setPhone("3017976885");
		customer.setToken("KDSJ230FOWEC02EW0DSPCAY6");

		entityManager.persist(customer);

	}

	@Test
	@Transactional
	@Order(2)
	void findById() {
		// Siga si no es nulo
		assertNotNull(entityManager, "EntityMager nulo");
		Customer customer = entityManager.find(Customer.class, email);

		// Siga si no es nulo
		assertNotNull(customer, "El cliente con email " + email + " ya existe");
		log.info(customer.getName());
	}
	
	@Test
	@Transactional
	@Order(3)
	void update() {
		// Siga si no es nulo
		assertNotNull(entityManager, "EntityMager nulo");
		Customer customer = entityManager.find(Customer.class, email);

		// Siga si no es nulo
		assertNotNull(customer, "El cliente con email " + email + " ya existe");
		
		customer.setEnable("N");
		
		log.info(customer.getName());
	}
	
	@Test
	@Transactional
	@Order(4)
	void delete() {
		// Siga si no es nulo
		assertNotNull(entityManager, "EntityMager nulo");
		Customer customer = entityManager.find(Customer.class, email);

		// Siga si no es nulo
		assertNotNull(customer, "El cliente con email " + email + " ya existe");
		
		entityManager.remove(customer);
		}
}
