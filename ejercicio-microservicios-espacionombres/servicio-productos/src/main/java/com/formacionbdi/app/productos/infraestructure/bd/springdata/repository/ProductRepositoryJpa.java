package com.formacionbdi.app.productos.infraestructure.bd.springdata.repository;

import com.formacionbdi.app.productos.infraestructure.bd.springdata.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepositoryJpa extends JpaRepository<ProductEntity, Long> {
}
