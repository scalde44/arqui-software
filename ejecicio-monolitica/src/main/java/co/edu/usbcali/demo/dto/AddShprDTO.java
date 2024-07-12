package co.edu.usbcali.demo.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddShprDTO {
	@NotNull
	private Integer carId;
	@NotNull
	@Size(min = 4, max = 255)
	@NotEmpty
	private String proId;
	@NotNull
	private Integer quantity;

	public AddShprDTO() {
		super();
	}

	public AddShprDTO(Integer carId, String proId, Integer quantity) {
		super();
		this.carId = carId;
		this.proId = proId;
		this.quantity = quantity;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
