package co.edu.usbcali.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import co.edu.usbcali.demo.domain.Customer;
import co.edu.usbcali.demo.dto.CustomerDTO;

@Mapper
public interface CustomerMapper {

	public CustomerDTO toCustomerDTO(Customer customer);
	
	public Customer toCustomer(CustomerDTO customerDTO);
	
	public List<CustomerDTO> toCustomersDTO(List<Customer> customers);
	
	public List<Customer> toCustomers(List<CustomerDTO> customersDTO);
}
