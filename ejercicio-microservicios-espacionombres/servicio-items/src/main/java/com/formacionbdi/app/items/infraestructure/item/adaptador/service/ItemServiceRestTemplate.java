package com.formacionbdi.app.items.infraestructure.item.adaptador.service;

import com.formacionbdi.app.items.domain.item.models.entity.Item;
import com.formacionbdi.app.items.domain.item.ports.service.ItemService;
import com.formacionbdi.app.items.domain.producto.models.dto.ProductoDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("itemServiceTemplate")
public class ItemServiceRestTemplate implements ItemService {
    private static final String URL_SERVICIO_PRODUCTOS = "http://servicio-productos";
    private static final String ENDPOINT_PRODUCTOS = "/product";
    private static final String ENDPOINT_ID = "/{id}";
    private final RestTemplate restTemplate;

    public ItemServiceRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Item> findAll() {
        List<ProductoDto> productos = Arrays.asList(this.restTemplate.getForObject(URL_SERVICIO_PRODUCTOS + ENDPOINT_PRODUCTOS, ProductoDto[].class));
        return productos.stream().map(productoDto -> new Item(productoDto, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantidad) {
        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("id", id.toString());
        ProductoDto productoDto = this.restTemplate.getForObject(URL_SERVICIO_PRODUCTOS + ENDPOINT_PRODUCTOS + ENDPOINT_ID, ProductoDto.class, pathVariables);
        return new Item(productoDto, cantidad);
    }
}
