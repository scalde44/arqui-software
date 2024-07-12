package co.edu.usbcali.demo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "shopping_cart", schema = "public")
public class ShoppingCart implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer carId;
	
	private Customer customer;
	
	private PaymentMethod paymentMethod;
	
	private Integer items;

	private Long total;
	
	private String enable;
	
	private List<ShoppingProduct> shoppingProducts = new ArrayList<ShoppingProduct>(0);

	public ShoppingCart() {
	}

	

	public ShoppingCart(Integer carId, Customer customer, PaymentMethod paymentMethod, Integer items, Long total,String enable, List<ShoppingProduct> shoppingProducts) {
		super();
		this.carId = carId;
		this.customer = customer;
		this.paymentMethod = paymentMethod;
		this.items = items;
		this.total = total;
		this.enable = enable;
		this.shoppingProducts = shoppingProducts;
	}



	@Id
	@Column(name = "car_id", unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getCarId() {
		return this.carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}
	
	@Column(name = "enable", nullable = false)
	public String getEnable() {
		return this.enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "email")
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pay_id")
	public PaymentMethod getPaymentMethod() {
		return this.paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Column(name = "items", nullable = false)
	public Integer getItems() {
		return this.items;
	}

	public void setItems(Integer items) {
		this.items = items;
	}

	@Column(name = "total", nullable = false)
	public Long getTotal() {
		return this.total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "shoppingCart")
	public List<ShoppingProduct> getShoppingProducts() {
		return this.shoppingProducts;
	}

	public void setShoppingProducts(List<ShoppingProduct> shoppingProducts) {
		this.shoppingProducts = shoppingProducts;
	}
}