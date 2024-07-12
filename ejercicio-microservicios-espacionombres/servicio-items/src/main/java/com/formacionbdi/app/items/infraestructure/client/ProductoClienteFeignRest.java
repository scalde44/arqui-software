package com.formacionbdi.app.items.infraestructure.client;

import com.formacionbdi.app.items.domain.producto.models.dto.ProductoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "servicio-productos", path = "product")
public interface ProductoClienteFeignRest {

    @GetMapping
    public List<ProductoDto> listar();

    @GetMapping("/{id}")
    public ProductoDto listarPorId(@PathVariable(name = "id") Long id);

    @PostMapping
    public ProductoDto guardar(@RequestBody ProductoDto productoDto);

    @PutMapping("/{id}")
    public ProductoDto actualizar(@RequestBody ProductoDto productoDto, @PathVariable Long id);

    @DeleteMapping("/{id}")
    public void eliminarPorId(@PathVariable Long id);
}
