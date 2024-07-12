package co.edu.usbcali.demo.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.demo.domain.Customer;
import co.edu.usbcali.demo.dto.CustomerDTO;
import co.edu.usbcali.demo.mapper.CustomerMapper;
import co.edu.usbcali.demo.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin("*")
public class CustomerController {

	private final static Logger log = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	CustomerService customerService;

	@Autowired
	CustomerMapper customerMappper;

	@PostMapping("/save")
	public ResponseEntity<?> save(@Valid @RequestBody CustomerDTO customerDTO) throws Exception {

		Customer customer = customerMappper.toCustomer(customerDTO);
		customer = customerService.save(customer);
		customerDTO = customerMappper.toCustomerDTO(customer);

		log.info("save");
		return ResponseEntity.ok().body(customerDTO);

	}

	@PutMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody CustomerDTO customerDTO) throws Exception {

		Customer customer = customerMappper.toCustomer(customerDTO);
		customer = customerService.update(customer);
		customerDTO = customerMappper.toCustomerDTO(customer);

		log.info("update");
		return ResponseEntity.ok().body(customerDTO);

	}

	@DeleteMapping("/delete/{email}")
	public ResponseEntity<?> delete(@PathVariable("email") String email) throws Exception {

		customerService.deleteById(email);

		return ResponseEntity.ok().build();

	}

	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() throws Exception {

		// Lista de Customers
		List<Customer> customers = customerService.findAll();
		// Declaro arreglo de DTOS
		List<CustomerDTO> customerDTOs = customerMappper.toCustomersDTO(customers);

		return ResponseEntity.ok().body(customerDTOs);

	}

	@GetMapping("/findById/{email}")
	public ResponseEntity<?> findById(@PathVariable("email") String email) throws Exception {

		Optional<Customer> customerOptional = customerService.findById(email);
		if (customerOptional.isPresent() == false) {
			return ResponseEntity.ok().body(null);
		}

		Customer customer = customerOptional.get();
		// Paso la informacion del Entity a el DTO
		CustomerDTO customerDTO = customerMappper.toCustomerDTO(customer);

		return ResponseEntity.ok().body(customerDTO);

	}
}
