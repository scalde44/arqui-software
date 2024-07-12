package com.formacionbdi.app.items.infraestructure.item.adaptador.service;

import com.formacionbdi.app.items.domain.item.models.entity.Item;
import com.formacionbdi.app.items.domain.item.ports.service.ItemService;
import com.formacionbdi.app.items.domain.producto.models.dto.ProductoDto;
import com.formacionbdi.app.items.infraestructure.client.ProductoClienteFeignRest;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("itemServiceFeign")
@Primary
public class ItemServiceFeign implements ItemService {
    private final ProductoClienteFeignRest feignRest;

    public ItemServiceFeign(ProductoClienteFeignRest feignRest) {
        this.feignRest = feignRest;
    }

    @Override
    public List<Item> findAll() {
        List<ProductoDto> productos = this.feignRest.listar();
        return productos.stream().map(productoDto -> new Item(productoDto, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantidad) {
        return new Item(this.feignRest.listarPorId(id), cantidad);
    }
}
