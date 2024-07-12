package co.edu.usbcali.demo.service;

import java.util.List;
import java.util.Optional;

public interface GenericService<T,ID> {
	
	public List<T> findAll();

    public T save(T entity) throws Exception;

    public T update(T entity) throws Exception;

    public void delete(T entity) throws Exception;

    public void deleteById(ID id) throws Exception;

    public Optional<T> findById(ID id) throws Exception;

    public void validate(T entity) throws Exception;

    public Long count();

}

