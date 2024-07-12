package co.edu.usbcali.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.Customer;
import co.edu.usbcali.demo.repository.CustomerRepository;

@Service
@Scope("singleton")
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	Validator validator;

	@Override
	public void validate(Customer entity) throws Exception {
		if (entity == null) {
			throw new Exception("El customer es nulo");
		}
		Set<ConstraintViolation<Customer>> constraintViolation = validator.validate(entity);
		if (constraintViolation.isEmpty() == false) {
			throw new ConstraintViolationException(constraintViolation);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Customer save(Customer entity) throws Exception {
		validate(entity);

		if (customerRepository.existsById(entity.getEmail())) {
			throw new Exception("El customer con id: " + entity.getEmail() + " ya existe");
		}

		return customerRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Customer update(Customer entity) throws Exception {
		validate(entity);

		if (customerRepository.existsById(entity.getEmail()) == false) {
			throw new Exception("El customer con id: " + entity.getEmail() + " no existe");
		}

		return customerRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Customer entity) throws Exception {
		if (entity == null) {
			throw new Exception("El customer es nulo");
		}
		if (entity.getEmail() == null || entity.getEmail().isBlank() == true) {
			throw new Exception("El email es obligatorio");
		}

		// Si no existe lanza error
		if (customerRepository.existsById(entity.getEmail()) == false) {
			throw new Exception("El customer con id: " + entity.getEmail() + " no existe");
		}

		// Valido referencias con otras tablas
		customerRepository.findById(entity.getEmail()).ifPresent(customer -> {
			if (customer.getShoppingCarts() != null && customer.getShoppingCarts().isEmpty() == false) {
				throw new RuntimeException(
						"El customer con id: " + entity.getEmail() + " tiene ShoppingCarts no se puede borrar");
			}
		});
		customerRepository.deleteById(entity.getEmail());
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(String id) throws Exception {
		if (id == null || id.isBlank() == true) {
			throw new Exception("El email es obligatorio");
		}

		if (customerRepository.existsById(id)) {
			delete(customerRepository.findById(id).get());
		} else {
			throw new Exception("El customer con id: " + id + " no existe");
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Customer> findById(String id) throws Exception {
		return customerRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return customerRepository.count();
	}

}
