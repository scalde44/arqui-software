package co.edu.usbcali.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.usbcali.demo.domain.Product;

public interface ProductRepository extends JpaRepository<Product, String>{
	
	//Buscar proId y name
	public List<Product> findByProIdAndName(String proId,String name);
	
	//Buscar precios mayores a
	public List<Product> findByPriceGreaterThan(Integer price);
	
	//Buscar precios menores a
	public List<Product> findByPriceLessThan(Integer price);
	
	//Buscar precios entre
	public List<Product> findByPriceBetween(Integer price1,Integer price2);
	
	//Ordenar por nombre like '% %' y ordenar por precios descendentes
	public List<Product> findByNameContainingOrderByPriceDesc(String name);

	//Ordenar por nombre like '% %' y ordenar por precios ascendentes
	public List<Product> findByNameContainingOrderByPriceAsc(String name);
	
	@Query("SELECT p FROM Product p WHERE LOWER(p.name) like LOWER(concat('%', :name,'%'))")
	public List<Product> findByLikeName(String name);
	
}
