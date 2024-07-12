package com.formacionbdi.app.productos.domain.producto.ports.repository;

import com.formacionbdi.app.productos.domain.producto.models.entity.Producto;

import java.util.List;

public interface ProductoRepository {
    public List<Producto> findAll();

    public Producto findById(Long id);

    public Producto save(Producto producto);

    public void deleteById(Long id);
}
