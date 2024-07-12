package com.formacionbdi.app.productos.infraestructure.producto.adapters.repository;

import com.formacionbdi.app.productos.domain.producto.models.entity.Producto;
import com.formacionbdi.app.productos.domain.producto.ports.repository.ProductoRepository;
import com.formacionbdi.app.productos.infraestructure.bd.springdata.entity.ProductEntity;
import com.formacionbdi.app.productos.infraestructure.bd.springdata.repository.ProductRepositoryJpa;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductoRepositoryImpl implements ProductoRepository {
    private final ProductRepositoryJpa productoRepositoryJpa;

    public ProductoRepositoryImpl(ProductRepositoryJpa productoRepositoryJpa) {
        this.productoRepositoryJpa = productoRepositoryJpa;
    }

    @Override
    public List<Producto> findAll() {
        return this.productoRepositoryJpa.findAll().stream()
                .map(product -> product.toProducto()).collect(Collectors.toList());
    }

    @Override
    public Producto findById(Long id) {
        return this.productoRepositoryJpa.findById(id)
                .map(product -> product.toProducto()).orElse(null);
    }

    @Override
    public Producto save(Producto producto) {
        return this.productoRepositoryJpa.save(new ProductEntity(producto)).toProducto();
    }

    @Override
    public void deleteById(Long id) {
        this.productoRepositoryJpa.deleteById(id);
    }
}
