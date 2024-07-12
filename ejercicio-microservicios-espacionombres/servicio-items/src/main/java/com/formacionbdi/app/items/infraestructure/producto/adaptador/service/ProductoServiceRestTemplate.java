package com.formacionbdi.app.items.infraestructure.producto.adaptador.service;

import com.formacionbdi.app.items.domain.producto.models.dto.ProductoDto;
import com.formacionbdi.app.items.domain.producto.ports.service.ProductoService;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service("productoServiceRestTemplate")
@Primary
public class ProductoServiceRestTemplate implements ProductoService {
    private static final String URL_SERVICIO_PRODUCTOS = "http://servicio-productos";
    private static final String ENDPOINT_PRODUCTOS = "/product";
    private static final String ENDPOINT_ID = "/{id}";
    private final RestTemplate restTemplate;

    public ProductoServiceRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ProductoDto save(ProductoDto productoDto) {
        HttpEntity<ProductoDto> body = new HttpEntity<ProductoDto>(productoDto);
        ResponseEntity<ProductoDto> response =
                this.restTemplate.exchange(URL_SERVICIO_PRODUCTOS + ENDPOINT_PRODUCTOS, HttpMethod.POST, body, ProductoDto.class);
        return response.getBody();
    }

    @Override
    public ProductoDto update(ProductoDto productoDto, Long id) {
        HttpEntity<ProductoDto> body = new HttpEntity<ProductoDto>(productoDto);
        ResponseEntity<ProductoDto> response =
                this.restTemplate.exchange(URL_SERVICIO_PRODUCTOS + ENDPOINT_PRODUCTOS + ENDPOINT_ID, HttpMethod.PUT, body, ProductoDto.class, pathVariableId(id));
        return response.getBody();
    }

    @Override
    public void deleteById(Long id) {
        this.restTemplate.delete(URL_SERVICIO_PRODUCTOS + ENDPOINT_PRODUCTOS + ENDPOINT_ID, pathVariableId(id));
    }

    private static Map<String, String> pathVariableId(Long id) {
        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("id", id.toString());
        return pathVariables;
    }
}
