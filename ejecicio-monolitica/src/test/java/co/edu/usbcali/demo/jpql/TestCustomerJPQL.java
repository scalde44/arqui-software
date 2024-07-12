package co.edu.usbcali.demo.jpql;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import co.edu.usbcali.demo.domain.Customer;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class TestCustomerJPQL {
	
	private final static Logger log=LoggerFactory.getLogger(TestCustomerJPQL.class);
	
	@Autowired
	private EntityManager entityManager;
	
	@BeforeEach
	public void beforeEach() {
		log.info("beforeEach");
		assertNotNull(entityManager,"El entityManger es nulo");
	}
	@Test
	public void selectAll() {
		log.info("selectAll");
		String jpql="SELECT cus FROM Customer cus";
		List<Customer> customers=entityManager.createQuery(jpql,Customer.class).getResultList();
		
		customers.forEach(customer->{
			log.info(customer.getEmail());
			log.info(customer.getName());
		});
		
		
	}
	@Test
	public void selectLike() {
		log.info("selectLike");
		String jpql="SELECT cus FROM Customer cus WHERE cus.name like 'Mel%'";
		List<Customer> customers=entityManager.createQuery(jpql,Customer.class).getResultList();
		
		customers.forEach(customer->{
			log.info(customer.getEmail());
			log.info(customer.getName());
		});
		
		
	}

	@Test
	public void selectWhereEnable() {
		log.info("selectWhereEnable");
		String jpql="SELECT cus FROM Customer cus WHERE cus.enable='N'";
		List<Customer> customers=entityManager.createQuery(jpql,Customer.class).getResultList();
		
		customers.forEach(customer->{
			log.info(customer.getEmail());
			log.info(customer.getName());
			log.info(customer.getEnable());
		});
		
		
	}
	@Test
	public void selectWhereParam() {
		log.info("selectWhereParam");
		String jpql="SELECT cus FROM Customer cus WHERE cus.enable=:enable AND cus.email=:email";
		List<Customer> customers=entityManager.
				createQuery(jpql,Customer.class).
				setParameter("enable", "N").
				setParameter("email", "bbendixency@gmpg.org").
				getResultList();
		
		customers.forEach(customer->{
			log.info(customer.getEmail());
			log.info(customer.getName());
			log.info(customer.getEnable());
		});
		
		
	}

}
