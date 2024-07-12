package co.edu.usbcali.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.usbcali.demo.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String>{
	
	//Buscar por enable y email
	public List<Customer> findByEnableAndEmail(String enable,String email);
	
	//Buscar por nombre like 'Mar%'
	@Query("SELECT cus FROM Customer cus WHERE cus.name like 'Mar%'")
	public List<Customer> findCustomerLikeMar();
	
	//Buscar por telefonos like '#%' (Que empiecen en #)
	public List<Customer> findByPhoneStartingWith(String phone);
	
	//Buscar por email like '% %'
	public List<Customer> findByEmailContaining(String email);
	
	//Buscar clientes habilitados y su nombre ordenado alfabeticamente
	List<Customer> findByEnableOrderByNameAsc(String enable);
	
}
