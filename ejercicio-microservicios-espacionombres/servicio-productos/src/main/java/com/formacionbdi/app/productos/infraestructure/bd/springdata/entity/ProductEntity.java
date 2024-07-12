package com.formacionbdi.app.productos.infraestructure.bd.springdata.entity;

import com.formacionbdi.app.productos.domain.producto.models.entity.Producto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    @Column(name = "create_at")
    private LocalDate createAt;

    public ProductEntity(Producto producto) {
        this.id = producto.getId();
        this.name = producto.getNombre();
        this.price = producto.getPrecio();
        this.createAt = producto.getFechaCreacion();
    }

    public Producto toProducto() {
        return new Producto(this.id, this.name, this.price, this.createAt);
    }
}
