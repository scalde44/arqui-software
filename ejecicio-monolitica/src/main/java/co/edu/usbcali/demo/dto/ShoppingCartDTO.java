package co.edu.usbcali.demo.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



public class ShoppingCartDTO {
    
    @NotNull
    private Integer carId;
    @NotNull
    @NotEmpty
    @Size(max = 1)
    private String enable;
    @NotNull
    private Integer items;
    @NotNull
    private Long total;
    @NotNull
    private String customerEmail;
    @NotNull
    private Integer paymentMethodId;
	public Integer getCarId() {
		return carId;
	}
	public void setCarId(Integer carId) {
		this.carId = carId;
	}
	public String getEnable() {
		return enable;
	}
	public void setEnable(String enable) {
		this.enable = enable;
	}
	public Integer getItems() {
		return items;
	}
	public void setItems(Integer items) {
		this.items = items;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public Integer getPaymentMethodId() {
		return paymentMethodId;
	}
	public void setPaymentMethodId(Integer paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}
    
    
}