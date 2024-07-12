package co.edu.usbcali.demo.repository;

import static org.junit.jupiter.api.Assertions.*;

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
import co.edu.usbcali.demo.domain.ShoppingCart;
import co.edu.usbcali.demo.domain.ShoppingProduct;

@SpringBootTest
@Rollback(false)
@TestMethodOrder(OrderAnnotation.class)
class ShoppingProductRepositoryTest {

	private final static Logger log=LoggerFactory.getLogger(ShoppingProductRepositoryTest.class);
	
	private static Integer shprId=null;
	
	private static String proId="APPL45";
	
	private static Integer carId=1;
	
	@Autowired
	ShoppingProductRepository shoppingProductRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ShoppingCartRepository shoppingCartRepository;
	
	@Test
	@Transactional
	@Order(1)
	void save() {
		ShoppingProduct shoppingProduct=new ShoppingProduct();
		shoppingProduct.setQuantity(3);
		shoppingProduct.setShprId(shprId);
		shoppingProduct.setTotal(9000000L);
		
		Optional<Product> productOptional=productRepository.findById(proId);
		assertTrue(productOptional.isPresent(),"El producto con id "+proId+" no existe");
		
		Product product=productOptional.get();
		shoppingProduct.setProduct(product);
		
		Optional<ShoppingCart> shoppingCartOptional=shoppingCartRepository.findById(carId);
		assertTrue(shoppingCartOptional.isPresent(),"El shoppingCart con id "+carId+" no existe");
		
		ShoppingCart shoppingCart=shoppingCartOptional.get();
		shoppingProduct.setShoppingCart(shoppingCart);
		
		shoppingProduct=shoppingProductRepository.save(shoppingProduct);
		shprId=shoppingProduct.getShprId();
		
		assertNotNull(shprId, "El shprId es nulo");
	}
	
	@Test
	@Transactional
	@Order(2)
	void findById() {
		Optional<ShoppingProduct> shoppingProductOptional=shoppingProductRepository.findById(shprId);
		assertTrue(shoppingProductOptional.isPresent(),"El shoppingProduct con shprId "+shprId+" no existe");
	}
	
	@Test
	@Transactional
	@Order(3)
	void update() {
		Optional<ShoppingProduct>  shoppingProductOptional=shoppingProductRepository.findById(shprId);
		assertTrue(shoppingProductOptional.isPresent(),"El shoppingProduct con shprId "+shprId+" no existe");
		
		ShoppingProduct shoppingProduct=shoppingProductOptional.get();
		shoppingProduct.setQuantity(5);
		
		shoppingProductRepository.save(shoppingProduct);
	}
	
	@Test
	@Transactional
	@Order(4)
	void delete() {
		Optional<ShoppingProduct>  shoppingProductOptional=shoppingProductRepository.findById(shprId);
		assertTrue(shoppingProductOptional.isPresent(),"El shoppingProduct con shprId "+shprId+" no existe");
		
		ShoppingProduct shoppingProduct=shoppingProductOptional.get();
		
		shoppingProductRepository.delete(shoppingProduct);
		
		log.info("delete");
	}

}
