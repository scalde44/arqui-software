package com.formacionbdi.app.items.infraestructure.producto.adaptador.service;

import com.formacionbdi.app.items.domain.producto.models.dto.ProductoDto;
import com.formacionbdi.app.items.domain.producto.ports.service.ProductoService;
import com.formacionbdi.app.items.infraestructure.client.ProductoClienteFeignRest;
import org.springframework.stereotype.Service;

@Service("productoServiceFeign")
public class ProductoServiceFeignClient implements ProductoService {
    private final ProductoClienteFeignRest feignRest;

    public ProductoServiceFeignClient(ProductoClienteFeignRest feignRest) {
        this.feignRest = feignRest;
    }

    @Override
    public ProductoDto save(ProductoDto productoDto) {
        return this.feignRest.guardar(productoDto);
    }

    @Override
    public ProductoDto update(ProductoDto productoDto, Long id) {
        return this.feignRest.actualizar(productoDto, id);
    }

    @Override
    public void deleteById(Long id) {
        this.feignRest.eliminarPorId(id);
    }
}
