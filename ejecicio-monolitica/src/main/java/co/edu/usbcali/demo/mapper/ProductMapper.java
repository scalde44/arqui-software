package co.edu.usbcali.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import co.edu.usbcali.demo.domain.Product;
import co.edu.usbcali.demo.dto.ProductDTO;

@Mapper
public interface ProductMapper {

	public ProductDTO toProductDTO(Product product);
	
	public Product toProduct(ProductDTO productDTO);
	
	public List<ProductDTO> toProductDTOs(List<Product> products);
	
	public List<Product> toProducts(List<ProductDTO> productDTOs);
}
