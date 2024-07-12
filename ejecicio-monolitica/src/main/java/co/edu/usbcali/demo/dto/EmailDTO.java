package co.edu.usbcali.demo.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmailDTO {
	@NotNull
	@Email
	@Size(min = 3, max = 255)
	private String email;

	public EmailDTO() {
		super();
	}

	public EmailDTO(String email) {
		super();
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
