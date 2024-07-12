package co.edu.usbcali.demo.service;

import java.util.List;

import co.edu.usbcali.demo.domain.Product;
import co.edu.usbcali.demo.domain.ShoppingCart;

public interface ShoppingCartService extends GenericService<ShoppingCart, Integer> {
	public List<ShoppingCart> findShcaByEmail(String email);

	public List<Product> findProductsByCarId(Integer carId);
	
	public List<ShoppingCart> findShcaByPayIdNull(String email);
}