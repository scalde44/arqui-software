package com.formacionbdi.app.items.domain.item.models.entity;

import com.formacionbdi.app.items.domain.producto.models.dto.ProductoDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item {
    private ProductoDto producto;
    private Integer cantidad;


    public Double getTotal() {
        return this.producto.getPrecio() * this.cantidad.doubleValue();
    }
}
