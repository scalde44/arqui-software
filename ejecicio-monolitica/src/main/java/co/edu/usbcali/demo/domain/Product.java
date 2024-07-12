package co.edu.usbcali.demo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "product", schema = "public")
public class Product implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "pro_id", unique = true, nullable = false)
	@NotNull
	@Size(min = 4, max = 255)
	@NotEmpty
	private String proId;

	@Column(name = "detail", nullable = false)
	@NotNull
	@Size(min = 3, max = 255)
	@NotEmpty
	private String detail;

	@Column(name = "enable", nullable = false)
	@NotNull
	@Size(min = 1, max = 1)
	@NotEmpty
	private String enable;

	@Column(name = "image", nullable = false)
	@NotNull
	@Size(min = 10, max = 255)
	@NotEmpty
	private String image;

	@Column(name = "name", nullable = false)
	@NotNull
	@Size(min = 4, max = 255)
	@NotEmpty
	private String name;

	@Column(name = "price", nullable = false)
	@Min(1)
	private Integer price;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	private List<ShoppingProduct> shoppingProducts = new ArrayList<ShoppingProduct>(0);

	public Product() {
	}

	public Product(String proId, String detail, String enable, String image, String name, Integer price,
			List<ShoppingProduct> shoppingProducts) {
		this.proId = proId;
		this.detail = detail;
		this.enable = enable;
		this.image = image;
		this.name = name;
		this.price = price;
		this.shoppingProducts = shoppingProducts;
	}

	public String getProId() {
		return this.proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getEnable() {
		return this.enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPrice() {
		return this.price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public List<ShoppingProduct> getShoppingProducts() {
		return this.shoppingProducts;
	}

	public void setShoppingProducts(List<ShoppingProduct> shoppingProducts) {
		this.shoppingProducts = shoppingProducts;
	}
}
