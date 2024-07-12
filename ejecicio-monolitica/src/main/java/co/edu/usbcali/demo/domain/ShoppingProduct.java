package co.edu.usbcali.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "shopping_product", schema = "public")
public class ShoppingProduct implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer shprId;

	private Product product;
	
	private ShoppingCart shoppingCart;
	
	private Integer quantity;
	
	private Long total;

	public ShoppingProduct() {
	}

	public ShoppingProduct(Integer shprId, Product product, Integer quantity, ShoppingCart shoppingCart, Long total) {
		this.shprId = shprId;
		this.product = product;
		this.shoppingCart = shoppingCart;
		this.quantity = quantity;
		this.total = total;
	}

	@Id
	@Column(name = "shpr_id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getShprId() {
		return this.shprId;
	}

	public void setShprId(Integer shprId) {
		this.shprId = shprId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pro_id")
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "car_id")
	public ShoppingCart getShoppingCart() {
		return this.shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	@Column(name = "quantity", nullable = false)
	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Column(name = "total", nullable = false)
	public Long getTotal() {
		return this.total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
}
