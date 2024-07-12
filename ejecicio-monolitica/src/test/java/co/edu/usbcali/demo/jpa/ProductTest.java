package co.edu.usbcali.demo.jpa;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.Product;

@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class ProductTest {

	private final static String proId = "APPL60";
	private final static Logger log=LoggerFactory.getLogger(ProductTest.class);
	
	@Autowired
	EntityManager entityManager;

	@Test
	@Transactional
	@Order(1)
	void save() {
		// Siga si no es nulo
		assertNotNull(entityManager, "EntityMager nulo");
		Product product = entityManager.find(Product.class, proId);

		// Siga si es nulo
		assertNull(product, "El producto con id " + proId + " ya existe");
		
		product = new Product();
		product.setProId(proId);
		product.setName("iWatch");
		product.setDetail("iWatch 3");
		product.setEnable("Y");
		product.setImage("https://shopping-cart-usb.s3.amazonaws.com/images/iphone-11-pro-select-2019-family.jpeg");
		product.setPrice(25000000);

		entityManager.persist(product);

	}
	
	@Test
	@Transactional
	@Order(2)
	void findById() {
		// Siga si no es nulo
		assertNotNull(entityManager, "EntityMager nulo");
		Product product=entityManager.find(Product.class, proId);
		assertNotNull(product,"El product con proId "+proId+" no existe");

	}
	
	@Test
	@Transactional
	@Order(3)
	void update() {
		// Siga si no es nulo
		assertNotNull(entityManager, "EntityMager nulo");
		Product product = entityManager.find(Product.class, proId);

		// Siga si no es nulo
		assertNotNull(product, "El producto con id " + proId + " no existe");
		
		product.setEnable("N");
		
		log.info(product.getName());
	}
	
	@Test
	@Transactional
	@Order(4)
	void delete() {
		// Siga si no es nulo
		assertNotNull(entityManager, "EntityMager nulo");
		Product product = entityManager.find(Product.class, proId);

		// Siga si no es nulo
		assertNotNull(product, "El producto con id " + proId + " ya existe");
		
		entityManager.remove(product);
		}
	

}
