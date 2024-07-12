package co.edu.usbcali.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.Customer;
import co.edu.usbcali.demo.domain.PaymentMethod;
import co.edu.usbcali.demo.domain.Product;
import co.edu.usbcali.demo.domain.ShoppingCart;
import co.edu.usbcali.demo.domain.ShoppingProduct;
import co.edu.usbcali.demo.repository.ShoppingCartRepository;

@Service
@Scope("singleton")
public class CartServiceImpl implements CartService {

	@Autowired
	CustomerService customerService;

	@Autowired
	ShoppingCartService shoppingCartService;
	
	@Autowired
	ShoppingCartRepository shoppingCartRepository;

	@Autowired
	ProductService productService;

	@Autowired
	ShoppingProductService shoppingProductService;

	@Autowired
	PaymentMethodService paymentMethodService;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ShoppingCart createCart(String email) throws Exception {
		Customer customer = null;

		if (email == null || email.isBlank() == true) {
			throw new Exception("El email del cliente es nulo");
		}

		Optional<Customer> customerOptional = customerService.findById(email);
		if (customerOptional.isPresent() == false) {
			throw new Exception("No existe un customer con el email: " + email);
		}

		customer = customerOptional.get();

		if (customer.getEnable() == null || customer.getEnable().equals("N") == true) {
			throw new Exception("El cliente con email: " + email + " no esta habilitado");
		}

		ShoppingCart shoppingCart = new ShoppingCart(0, customer, null, 0, 0L, "Y", null);

		shoppingCart = shoppingCartService.save(shoppingCart);

		return shoppingCart;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ShoppingProduct addProduct(Integer carId, String proId, Integer quantity) throws Exception {

		ShoppingCart shoppingCart = null;
		Product product = null;
		ShoppingProduct shoppingProduct = null;
		Long totalShoppingProduct = 0L;
		Long totalShoppingCart = 0L;
		Integer itemsShoppingCart = 0;
		Integer cantidadShpr = 0;

		if (carId == null || carId <= 0) {
			throw new Exception("El carId es nulo o menor a cero");
		}

		if (proId == null || proId.isBlank() == true) {
			throw new Exception("El proId es nulo o menor a esta en blanco");
		}

		if (quantity == null || quantity <= 0) {
			throw new Exception("El quantity es nulo o menor a cero");
		}

		if (shoppingCartService.findById(carId).isPresent() == false) {
			throw new Exception("El shoppingCart con carId no existe");
		}

		shoppingCart = shoppingCartService.findById(carId).get();

		if (shoppingCart.getEnable().equals("N") == true) {
			throw new Exception("El shoppingCart esta inhabilitado");
		}

		if (productService.findById(proId).isPresent() == false) {
			throw new Exception("El product no existe");
		}

		product = productService.findById(proId).get();

		if (product.getEnable().equals("N") == true) {
			throw new Exception("El product esta inhabilitado");
		}

		shoppingProduct = shoppingProductService.getShprByCarPro(carId, proId);

		if (shoppingProduct == null) {
			shoppingProduct = new ShoppingProduct();
			shoppingProduct.setProduct(product);
			shoppingProduct.setQuantity(quantity);
			shoppingProduct.setShoppingCart(shoppingCart);
			shoppingProduct.setShprId(0);
			totalShoppingProduct = Long.valueOf(product.getPrice() * quantity);
			shoppingProduct.setTotal(totalShoppingProduct);
			shoppingProduct = shoppingProductService.save(shoppingProduct);
		} else {
			cantidadShpr = shoppingProduct.getQuantity() + quantity;
			totalShoppingProduct = Long.valueOf(product.getPrice() * cantidadShpr);
			shoppingProduct.setQuantity(cantidadShpr);
			shoppingProduct.setTotal(totalShoppingProduct);
			shoppingProduct = shoppingProductService.update(shoppingProduct);
		}

		totalShoppingCart = shoppingProductService.totalShoppingProductByShoppingCart(carId);
		itemsShoppingCart = shoppingProductService.totalItems(carId);
		shoppingCart.setTotal(totalShoppingCart);
		shoppingCart.setItems(itemsShoppingCart);
		shoppingCartService.update(shoppingCart);

		return shoppingProduct;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void removeProduct(Integer carId, String proId) throws Exception {
		ShoppingCart shoppingCart = null;
		Product product = null;
		ShoppingProduct shoppingProduct = null;
		Long totalShoppingCart = 0L;
		Integer itemsShoppingCart = 0;
		if (carId == null || carId <= 0) {
			throw new Exception("El carId es nulo o menor a cero");
		}

		if (proId == null || proId.isBlank() == true) {
			throw new Exception("El proId es nulo o menor o esta en blanco");
		}
		if (shoppingCartService.findById(carId).isPresent() == false) {
			throw new Exception("El shoppingCart con carId " + carId + " no existe");
		}
		shoppingCart = shoppingCartService.findById(carId).get();
		if (shoppingCart.getEnable().equals("N") == true) {
			throw new Exception("El shoppingCart esta inhabilitado");
		}
		if (productService.findById(proId).isPresent() == false) {
			throw new Exception("El product no existe");
		}

		product = productService.findById(proId).get();

		if (product.getEnable().equals("N") == true) {
			throw new Exception("El product esta inhabilitado");
		}
		shoppingProduct = shoppingProductService.getShprByCarPro(carId, proId);
		if (shoppingProduct == null) {
			throw new Exception("No hay ningun shoppingProduct con carId: " + carId + " y proId: " + proId);
		}
		shoppingProductService.delete(shoppingProduct);
		totalShoppingCart = shoppingProductService.totalShoppingProductByShoppingCart(carId);
		itemsShoppingCart = shoppingProductService.totalItems(carId);
		if (totalShoppingCart == null || itemsShoppingCart == null) {
			totalShoppingCart = 0L;
			itemsShoppingCart = 0;
		}
		shoppingCart.setTotal(totalShoppingCart);
		shoppingCart.setItems(itemsShoppingCart);
		shoppingCartService.update(shoppingCart);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void clearCart(Integer carId) throws Exception {
		ShoppingCart shoppingCart = null;
		List<Integer> shprsId = null;
		if (carId == null || carId <= 0) {
			throw new Exception("El carId es nulo o menor a cero");
		}

		if (shoppingCartService.findById(carId).isPresent() == false) {
			throw new Exception("El shoppingCart con carId " + carId + " no existe");
		}
		shoppingCart = shoppingCartService.findById(carId).get();
		if (shoppingCart.getEnable().equals("N") == true) {
			throw new Exception("El shoppingCart esta inhabilitado");
		}

		shprsId = shoppingProductService.getShprIdByCarId(carId);
		if (shprsId.isEmpty() == true || shprsId == null) {
			throw new Exception("No hay ningun shoppingProduct con carId: " + carId);
		}
		shprsId.forEach(shprId -> {
			try {
				shoppingProductService.deleteById(shprId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		shoppingCart.setTotal(0L);
		shoppingCart.setItems(0);
		shoppingCartService.update(shoppingCart);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ShoppingProduct> findShoppingProductByShoppingCart(Integer carId) throws Exception {
		ShoppingCart shoppingCart = null;
		if (carId == null || carId <= 0) {
			throw new Exception("El carId es nulo o menor a cero");
		}

		if (shoppingCartService.findById(carId).isPresent() == false) {
			throw new Exception("El shoppingCart con carId " + carId + " no existe");
		}
		shoppingCart = shoppingCartService.findById(carId).get();
		if (shoppingCart.getEnable().equals("N") == true) {
			throw new Exception("El shoppingCart esta inhabilitado");
		}
		return shoppingProductService.findShprByCarId(carId);
	}

	@Override
	public List<ShoppingCart> findShoppingCartByEmail(String email) throws Exception {
		Customer customer = null;

		if (email == null || email.isBlank() == true) {
			throw new Exception("El email del cliente es nulo");
		}

		Optional<Customer> customerOptional = customerService.findById(email);
		if (customerOptional.isPresent() == false) {
			throw new Exception("No existe un customer con el email: " + email);
		}

		customer = customerOptional.get();

		if (customer.getEnable() == null || customer.getEnable().equals("N") == true) {
			throw new Exception("El cliente con email: " + email + " no esta habilitado");
		}
		return shoppingCartService.findShcaByEmail(email);
	}

	@Override
	public ShoppingCart finalizarCompra(Integer carId, Integer payId) throws Exception {
		ShoppingCart shoppingCart = null;
		PaymentMethod paymentMethod = null;
		if (carId == null || carId <= 0) {
			throw new Exception("El carId es nulo o menor a cero");
		}

		if (payId == null || payId <= 0) {
			throw new Exception("El payId es nulo o menor a cero");
		}

		if (shoppingCartService.findById(carId).isPresent() == false) {
			throw new Exception("El shoppingCart con carId " + carId + " no existe");
		}
		shoppingCart = shoppingCartService.findById(carId).get();
		if (shoppingCart.getEnable().equals("N") == true) {
			throw new Exception("El shoppingCart esta inhabilitado");
		}
		if (paymentMethodService.findById(payId).isPresent() == false) {
			throw new Exception("El paymentMethod no existe");
		}

		paymentMethod = paymentMethodService.findById(payId).get();

		if (paymentMethod.getEnable().equals("N") == true) {
			throw new Exception("El paymentMethod esta inhabilitado");
		}
		shoppingCart.setPaymentMethod(paymentMethod);
		shoppingCartService.update(shoppingCart);
		return shoppingCart;
	}

	@Override
	public List<ShoppingCart> findShcaByPayIdNull(String email) throws Exception{
		Customer customer = null;

		if (email == null || email.isBlank() == true) {
			throw new Exception("El email del cliente es nulo");
		}

		Optional<Customer> customerOptional = customerService.findById(email);
		if (customerOptional.isPresent() == false) {
			throw new Exception("No existe un customer con el email: " + email);
		}

		customer = customerOptional.get();

		if (customer.getEnable() == null || customer.getEnable().equals("N") == true) {
			throw new Exception("El cliente con email: " + email + " no esta habilitado");
		}
		return shoppingCartRepository.findShcaByPayIdNull(email);
	}

}
