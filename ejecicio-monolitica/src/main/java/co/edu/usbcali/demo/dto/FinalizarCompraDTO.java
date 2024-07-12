package co.edu.usbcali.demo.dto;

import javax.validation.constraints.NotNull;

public class FinalizarCompraDTO {
	@NotNull
	private Integer carId;
	@NotNull
	private Integer payId;

	public FinalizarCompraDTO() {
		super();
	}

	public FinalizarCompraDTO(Integer carId, Integer payId) {
		super();
		this.carId = carId;
		this.payId = payId;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public Integer getPayId() {
		return payId;
	}

	public void setPayId(Integer payId) {
		this.payId = payId;
	}
}
