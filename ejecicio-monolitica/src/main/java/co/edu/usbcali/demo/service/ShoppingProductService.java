package co.edu.usbcali.demo.service;

import java.util.List;

import co.edu.usbcali.demo.domain.ShoppingProduct;

public interface ShoppingProductService extends GenericService<ShoppingProduct, Integer> {

	public Long totalShoppingProductByShoppingCart(Integer carId);

	public Integer totalItems(Integer carId);

	public ShoppingProduct getShprByCarPro(Integer carId, String proId);

	public List<Integer> getShprIdByCarId(Integer carId);

	public List<ShoppingProduct> findShprByCarId(Integer carId);
	
	

}