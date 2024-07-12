package com.formacionbdi.app.items.domain.producto.ports.service;

import com.formacionbdi.app.items.domain.producto.models.dto.ProductoDto;

public interface ProductoService {
    public ProductoDto save(ProductoDto productoDto);

    public ProductoDto update(ProductoDto productoDto, Long id);

    public void deleteById(Long id);
}
