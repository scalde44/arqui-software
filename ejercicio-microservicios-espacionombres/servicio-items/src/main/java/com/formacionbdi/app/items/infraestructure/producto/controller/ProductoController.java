package com.formacionbdi.app.items.infraestructure.producto.controller;

import com.formacionbdi.app.items.domain.producto.models.dto.ProductoDto;
import com.formacionbdi.app.items.domain.producto.ports.service.ProductoService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("producto")
public class ProductoController {
    @Qualifier("productoServiceFeign")
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductoDto save(@RequestBody ProductoDto productoDto) {
        return this.productoService.save(productoDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductoDto update(@RequestBody ProductoDto productoDto, @PathVariable Long id) {
        return this.productoService.update(productoDto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        this.productoService.deleteById(id);
    }
}
