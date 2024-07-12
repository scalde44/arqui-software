package co.edu.usbcali.demo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CustomerDTO {

	@NotNull
	@Email
	@Size(min = 3, max = 255)
	private String email;

	@NotNull
	@Size(min = 3, max = 255)
	@NotEmpty
	private String address;

	@NotNull
	@Size(min = 1, max = 1)
	@NotEmpty
	private String enable;

	@NotNull
	@Size(min = 4, max = 255)
	@NotEmpty
	private String name;

	@NotNull
	@Size(min = 6, max = 255)
	@NotEmpty
	private String phone;

	@NotNull
	@Size(max = 255)
	@NotEmpty
	private String token;

	@NotNull
	@Size(min = 1, max = 1)
	@NotEmpty
	private String role;

	public CustomerDTO() {
		super();
	}

	public CustomerDTO(String email, String address, String enable, String name, String phone, String token,
			String role) {
		super();
		this.email = email;
		this.address = address;
		this.enable = enable;
		this.name = name;
		this.phone = phone;
		this.token = token;
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
