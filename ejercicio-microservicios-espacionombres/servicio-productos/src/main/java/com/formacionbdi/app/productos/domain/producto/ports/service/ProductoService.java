package com.formacionbdi.app.productos.domain.producto.ports.service;

import com.formacionbdi.app.productos.domain.producto.models.dto.ProductoDto;

import java.util.List;

public interface ProductoService {
    public List<ProductoDto> findAll();

    public ProductoDto findById(Long id);

    public ProductoDto save(ProductoDto productoDto);

    public ProductoDto update(ProductoDto productoDto, Long id);

    public void deleteById(Long id);
}
