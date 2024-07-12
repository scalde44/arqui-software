package co.edu.usbcali.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
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
class CustomerRepositoryTest {
	private final static String email = "scalderon1504@gmail.com";
	private final static Logger log = LoggerFactory.getLogger(CustomerRepositoryTest.class);

	@Autowired
	CustomerRepository customerRepository;

	@Test
	@Transactional
	@Order(1)
	void save() {
		log.info("save");

		Optional<Customer> customerOptional = customerRepository.findById(email);

		assertFalse(customerOptional.isPresent(), "El cliente ya existe");
		Customer customer = new Customer();
		customer.setAddress("Avenida siempre viva 123");
		customer.setEmail(email);
		customer.setEnable("Y");
		customer.setName("Steven Calderon");
		customer.setPhone("3017976885");
		customer.setToken("KDSJ230FOWEC02EW0DSPCAY6");

		customerRepository.save(customer);
	}

	@Test
	@Transactional
	@Order(2)
	void findById() {
		log.info("findById");

		Optional<Customer> customerOptional = customerRepository.findById(email);

		assertTrue(customerOptional.isPresent(), "El cliente no existe");

	}
	
	@Test
	@Transactional
	@Order(3)
	void update() {
		log.info("update");

		Optional<Customer> customerOptional = customerRepository.findById(email);

		assertTrue(customerOptional.isPresent(), "El cliente no existe");
		
		Customer customer=customerOptional.get();
		customer.setEnable("N");
		customerRepository.save(customer);

	}
	
	@Test
	@Transactional
	@Order(4)
	void delete() {
		log.info("delete");

		Optional<Customer> customerOptional = customerRepository.findById(email);

		assertTrue(customerOptional.isPresent(), "El cliente no existe");
		
		Customer customer=customerOptional.get();
		customerRepository.delete(customer);

	}
	
	@Test
	@Transactional
	@Order(5)
	void findAll() {
		log.info("findAll");
		customerRepository.findAll().forEach(customer->{
			log.info("Name: "+customer.getName());
			log.info("Email: "+customer.getEmail());
		});
	}
	
	@Test
	@Transactional
	@Order(6)
	void count() {
		log.info("Count: "+customerRepository.count());
	}

	@Test
	@Transactional
	@Order(7)
	void findByEnableAndEmail() {
		log.info("findByEnableAndEmail");
		List<Customer> customers=customerRepository.findByEnableAndEmail("N", email);
		assertFalse(customers.isEmpty());
		customers.forEach(customer->{
			log.info("Email: "+customer.getEmail());
			log.info("Name: "+customer.getName());
			log.info("Enable: "+customer.getEnable());
		});
	}
	
	@Test
	@Transactional
	@Order(8)
	void findByLikeMar() {
		log.info("findByLikeMar");
		List<Customer> customers=customerRepository.findCustomerLikeMar();
		assertFalse(customers.isEmpty());
		customers.forEach(customer->{
			log.info("Email: "+customer.getEmail());
			log.info("Name: "+customer.getName());
			log.info("Enable: "+customer.getEnable());
		});
	}
	
	@Test
	@Transactional
	@Order(9)
	void findByPhoneStartingWith() {
		log.info("findByPhoneStartingWith");
		
		List<Customer> customers=customerRepository.findByPhoneStartingWith("6");
		
		assertFalse(customers.isEmpty());
		
		customers.forEach(customer->{
			log.info("Email: "+customer.getEmail());
			log.info("Name: "+customer.getName());
			log.info("Phone: "+customer.getPhone());
		});
	}
	
	@Test
	@Transactional
	@Order(10)
	void findByEmailContaining() {
		log.info("findByEmailContaining");
		
		List<Customer> customers=customerRepository.findByEmailContaining("wordpress");
		
		assertFalse(customers.isEmpty());
		
		customers.forEach(customer->{
			log.info("Email: "+customer.getEmail());
			log.info("Name: "+customer.getName());
		});
	}
	
	@Test
	@Transactional
	@Order(11)
	void findByEnableOrderByNameAsc() {
		log.info("findByEnableOrderByNameAsc");
		
		List<Customer> customers=customerRepository.findByEnableOrderByNameAsc("Y");
		
		assertFalse(customers.isEmpty());
		
		customers.forEach(customer->{
			log.info("Name: "+customer.getName());
			log.info("Enable: "+customer.getEnable());
		});
	}
}
