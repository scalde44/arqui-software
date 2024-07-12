package com.formacionbdi.app.productos.domain.producto.models.dto;

import com.formacionbdi.app.productos.domain.producto.models.entity.Producto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Builder
public class ProductoDto {
    private Long id;
    private String nombre;
    private Double precio;
    private LocalDate fechaCreacion;

    public ProductoDto(Producto producto) {
        this.id = producto.getId();
        this.nombre = producto.getNombre();
        this.precio = producto.getDoubleValuePrecio();
        this.fechaCreacion = producto.getFechaCreacion();
    }
}
