package com.formacionbdi.app.productos.infraestructure.producto.controller;

import com.formacionbdi.app.productos.domain.producto.models.dto.ProductoDto;
import com.formacionbdi.app.productos.domain.producto.ports.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductoDto> findAll() {
        return this.productoService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductoDto findById(@PathVariable(name = "id") Long id) {
        return this.productoService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductoDto save(@RequestBody ProductoDto productoDto) {
        return this.productoService.save(productoDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        this.productoService.deleteById(id);
    }

    @PutMapping("/id")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductoDto update(@RequestBody ProductoDto productoDto, @PathVariable Long id) {
        return this.productoService.update(productoDto, id);
    }
}
