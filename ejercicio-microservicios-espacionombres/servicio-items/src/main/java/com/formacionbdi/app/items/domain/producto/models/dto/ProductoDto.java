package com.formacionbdi.app.items.domain.producto.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDto {
    private Long id;
    private String nombre;
    private Double precio;
    private LocalDate fechaCreacion;
}
