package co.edu.usbcali.demo.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.Product;

@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class ProductRepositoryTest {

	private final static String proId = "APPL60";
	Logger log = LoggerFactory.getLogger(ProductRepositoryTest.class);
	
	@Autowired
	ProductRepository productRepository;
	
	@Test
	@Transactional
	@Order(1)
	void save() {
		log.info("save");
		
		Optional<Product> productOptional=productRepository.findById(proId);
		
		assertFalse(productOptional.isPresent(),"El producto ya existe");
		Product product=new Product();
		product.setProId(proId);
		product.setName("iWatch 3");
		product.setDetail("Nuevo iWacth3");
		product.setEnable("Y");
		product.setPrice(2500000);
		product.setImage("https://shopping-cart-usb.s3.amazonaws.com/images/iphone-11-pro-select-2019-family.jpeg");
		
		productRepository.save(product);
	}
	
	@Test
	@Transactional
	@Order(2)
	void findById() {
		log.info("findById");
		
		Optional<Product> productOptional=productRepository.findById(proId);
		
		assertTrue(productOptional.isPresent(),"El producto no existe");
	}
	
	@Test
	@Transactional
	@Order(3)
	void update() {
		log.info("update");
		
		Optional<Product> productOptional=productRepository.findById(proId);
		
		assertTrue(productOptional.isPresent(),"El producto no existe");
		
		Product product=productOptional.get();
		product.setEnable("N");
		
		productRepository.save(product);
	}
	
	@Test
	@Transactional
	@Order(4)
	void delete() {
		log.info("delete");
		
		Optional<Product> productOptional=productRepository.findById(proId);
		
		assertTrue(productOptional.isPresent(),"El producto no existe");
		
		Product product=productOptional.get();
		
		productRepository.delete(product);
	}
	
	@Test
	@Transactional
	@Order(5)
	void findAll() {
		log.info("findAll");
		
		productRepository.findAll().forEach(product->{
			log.info("proId: "+product.getProId());
			log.info("Name: "+product.getName());
			log.info("Price: "+product.getPrice());
		});
	}
	
	@Test
	@Transactional
	@Order(6)
	void count() {
		log.info("count: "+productRepository.count());
	}
	
	@Test
	@Transactional
	@Order(7)
	void findByProIdAndName() {
		log.info("findByProIdAndName");
		
		List<Product> products=productRepository.findByProIdAndName("APPL45", "iPhone X");
		
		assertFalse(products.isEmpty());
		
		products.forEach(product->{
			log.info("Pro Id: "+product.getProId());
			log.info("Name: "+product.getName());
		});
		
	}
	
	@Test
	@Transactional
	@Order(8)
	void findByPriceGreaterThan() {
		log.info("findByPriceGreaterThan");
		
		List<Product> products= productRepository.findByPriceGreaterThan(4450000);
		
		assertFalse(products.isEmpty());
		
		products.forEach(product->{
			log.info("Pro Id: "+product.getProId());
			log.info("Name: "+product.getName());
			log.info("Price: "+product.getPrice());
		});
	}
	
	@Test
	@Transactional
	@Order(9)
	void findByPriceLessThan() {
		log.info("findByPriceGreaterThan");
		
		List<Product> products= productRepository.findByPriceLessThan(4450000);
		
		assertFalse(products.isEmpty());
		
		products.forEach(product->{
			log.info("Pro Id: "+product.getProId());
			log.info("Name: "+product.getName());
			log.info("Price: "+product.getPrice());
		});
	}
	
	@Test
	@Transactional
	@Order(10)
	void findByPriceBetween() {
		log.info("findByPriceBetween");
		
		List<Product> products=productRepository.findByPriceBetween(3000000, 4600000);
		
		assertFalse(products.isEmpty());
		
		products.forEach(product->{
			log.info("Pro Id: "+product.getProId());
			log.info("Name: "+product.getName());
			log.info("Price: "+product.getPrice());
		});
	}
	
	@Test
	@Transactional
	@Order(11)
	void findByNameContainingOrderByPriceDesc(){
		log.info("findByNameContainingOrderByPriceDesc");
		
		List<Product> products=productRepository.findByNameContainingOrderByPriceDesc("i");
		
		assertFalse(products.isEmpty());
		
		products.forEach(product->{
			log.info("Pro Id: "+product.getProId());
			log.info("Name: "+product.getName());
			log.info("Price: "+product.getPrice());
		});
	}
	
	@Test
	@Transactional
	@Order(12)
	void findByNameContainingOrderByPriceAsc() {
		log.info("findByNameContainingOrderByPriceAsc");
		
		List<Product> products=productRepository.findByNameContainingOrderByPriceAsc("i");
		
		assertFalse(products.isEmpty());
		
		products.forEach(product->{
			log.info("Pro Id: "+product.getProId());
			log.info("Name: "+product.getName());
			log.info("Price: "+product.getPrice());
		});
	}
}

