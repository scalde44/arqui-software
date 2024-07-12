package co.edu.usbcali.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.demo.domain.ShoppingProduct;
import co.edu.usbcali.demo.repository.ShoppingProductRepository;

@Scope("singleton")
@Service
public class ShoppingProductServiceImpl implements ShoppingProductService {

	private final static Logger log = LoggerFactory.getLogger(ShoppingProductServiceImpl.class);

	@Autowired
	private ShoppingProductRepository shoppingProductRepository;
	@Autowired
	private Validator validator;

	@Override
	public void validate(ShoppingProduct shoppingProduct) throws ConstraintViolationException {
		Set<ConstraintViolation<ShoppingProduct>> constraintViolations = validator.validate(shoppingProduct);

		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return shoppingProductRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<ShoppingProduct> findAll() {
		log.debug("finding all ShoppingProduct instances");

		return shoppingProductRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ShoppingProduct save(ShoppingProduct entity) throws Exception {
		log.debug("saving ShoppingProduct instance");

		if (entity == null) {
			throw new Exception("El ShoppingProduct es nulo");
		}

		validate(entity);

		if (shoppingProductRepository.existsById(entity.getShprId())) {
			throw new Exception("El ShoppingProduct con id:" + entity.getShprId() + " Ya existe");
		}

		return shoppingProductRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(ShoppingProduct entity) throws Exception {
		log.debug("deleting ShoppingProduct instance");

		if (entity == null) {
			throw new Exception("El ShoppingProduct es nulo");
		}

		if (entity.getShprId() == null) {
			throw new Exception("El ShoppingProduct id es nulo");
		}

		if (shoppingProductRepository.existsById(entity.getShprId()) == false) {
			throw new Exception("El ShoppingProduct con id:" + entity.getShprId() + " No existe");
		}

		shoppingProductRepository.deleteById(entity.getShprId());
		log.debug("delete ShoppingProduct successful");
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Integer id) throws Exception {
		log.debug("deleting ShoppingProduct instance");

		if (id == null) {
			throw new Exception("El ShoppingProduct id es nulo");
		}

		if (shoppingProductRepository.existsById(id)) {
			delete(shoppingProductRepository.findById(id).get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public ShoppingProduct update(ShoppingProduct entity) throws Exception {
		log.debug("updating ShoppingProduct instance");

		if (entity == null) {
			throw new Exception("El ShoppingProduct es nulo");
		}

		validate(entity);

		if (shoppingProductRepository.existsById(entity.getShprId()) == false) {
			throw new Exception("El ShoppingProduct con id:" + entity.getShprId() + " No existe");
		}

		return shoppingProductRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<ShoppingProduct> findById(Integer shprId) throws Exception {
		log.debug("getting ShoppingProduct instance");

		return shoppingProductRepository.findById(shprId);
	}

	@Override
	@Transactional(readOnly = true)
	public Long totalShoppingProductByShoppingCart(Integer carId) {
		return shoppingProductRepository.totalShoppingProductByShoppingCart(carId);
	}

	@Override
	public Integer totalItems(Integer carId) {
		return shoppingProductRepository.totalItems(carId);
	}

	

	@Override
	public List<Integer> getShprIdByCarId(Integer carId) {
		return shoppingProductRepository.getShprIdByCarId(carId);
	}

	@Override
	public List<ShoppingProduct> findShprByCarId(Integer carId) {
		return shoppingProductRepository.findShprByCarId(carId);
	}

	@Override
	public ShoppingProduct getShprByCarPro(Integer carId, String proId) {
		return shoppingProductRepository.getShprByCarPro(carId, proId);
	}

	
}