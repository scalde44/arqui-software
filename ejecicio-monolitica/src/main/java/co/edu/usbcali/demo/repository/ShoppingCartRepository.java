package co.edu.usbcali.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.usbcali.demo.domain.Product;
import co.edu.usbcali.demo.domain.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer>{
	@Query("SELECT shca FROM ShoppingCart shca WHERE shca.customer.email=:email")
	public List<ShoppingCart> findShcaByEmail(String email);
	@Query("SELECT pro FROM ShoppingCart shca JOIN ShoppingProduct shpr ON shca.carId=:carId JOIN Product pro ON pro.proId=shpr.product.proId")
	public List<Product> findProductsByCarId(Integer carId);
	@Query("SELECT shca FROM ShoppingCart shca WHERE shca.customer.email=:email AND shca.paymentMethod.payId IS NULL")
	public List<ShoppingCart> findShcaByPayIdNull(String email);
}
