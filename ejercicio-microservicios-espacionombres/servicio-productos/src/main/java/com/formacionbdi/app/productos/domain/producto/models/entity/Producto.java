package com.formacionbdi.app.productos.domain.producto.models.entity;

import com.formacionbdi.app.productos.domain.producto.models.dto.ProductoDto;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;


@Data
public class Producto implements Serializable {
    private Long id;
    private String nombre;
    private BigDecimal precio;
    private LocalDate fechaCreacion;

    public Producto(Long id, String nombre, BigDecimal precio, LocalDate fechaCreacion) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.fechaCreacion = fechaCreacion;
    }

    public Double getDoubleValuePrecio() {
        return this.precio.doubleValue();
    }

    public Producto(ProductoDto productoDto) {
        this.nombre = productoDto.getNombre();
        this.precio = new BigDecimal(productoDto.getPrecio());
        this.fechaCreacion = LocalDate.now();
    }

    public ProductoDto toProductoDto() {
        return new ProductoDto(this);
    }
}
