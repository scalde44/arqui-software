package co.edu.usbcali.demo.dto;

import javax.validation.constraints.NotNull;

public class ShoppingProductDTO {

	@NotNull
	private Integer quantity;
	@NotNull
	private Integer shprId;
	@NotNull
	private Long total;
	@NotNull
	private String productId;
	@NotNull
	private Integer shoppingCartId;
	@NotNull
	private String productName;
	@NotNull
	private String productImage;
	@NotNull
	private Integer productPrice;

	
	public ShoppingProductDTO() {
		super();
	}

	public ShoppingProductDTO(@NotNull Integer quantity, @NotNull Integer shprId, @NotNull Long total,
			@NotNull String productId, @NotNull Integer shoppingCartId, @NotNull String productName,
			@NotNull String productImage, @NotNull Integer productPrice) {
		super();
		this.quantity = quantity;
		this.shprId = shprId;
		this.total = total;
		this.productId = productId;
		this.shoppingCartId = shoppingCartId;
		this.productName = productName;
		this.productImage = productImage;
		this.productPrice = productPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getShprId() {
		return shprId;
	}

	public void setShprId(Integer shprId) {
		this.shprId = shprId;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getShoppingCartId() {
		return shoppingCartId;
	}

	public void setShoppingCartId(Integer shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public Integer getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}
	

}
