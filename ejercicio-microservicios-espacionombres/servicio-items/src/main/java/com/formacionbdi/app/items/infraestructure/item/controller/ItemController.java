package com.formacionbdi.app.items.infraestructure.item.controller;

import com.formacionbdi.app.items.domain.item.models.entity.Item;
import com.formacionbdi.app.items.domain.item.ports.service.ItemService;
import com.formacionbdi.app.items.domain.producto.models.dto.ProductoDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RefreshScope
@RestController
@RequestMapping("item")
public class ItemController {
    private static final String NAME_CIRCUIT = "item";
    private static final Logger log = LoggerFactory.getLogger(ItemController.class);

    @Value("${configuracion.texto}")
    private String texto;

    private final Environment environment;
    @Qualifier("itemServiceFeign")
    private final ItemService itemService;
    private final CircuitBreakerFactory circuitBreakerFactory;

    public ItemController(Environment environment, ItemService itemService, CircuitBreakerFactory circuitBreakerFactory) {
        this.environment = environment;
        this.itemService = itemService;
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    @GetMapping
    public List<Item> findAll(@RequestParam(name = "nombre", required = false) String nombre,
                              @RequestHeader(name = "token-request", required = false) String token) {
        log.info("nombre = " + nombre);
        log.info("token = " + token);
        return this.itemService.findAll();
    }

    // Configuracion circuitbreaker manual
    //@HystrixCommand(fallbackMethod = "metodoAlternativo")
    @GetMapping("/{id}/{cantidad}")
    public Item detalleImplementacionConfig(@PathVariable(name = "id") Long id, @PathVariable(name = "cantidad") Integer cantidad) {
        return this.circuitBreakerFactory.create(NAME_CIRCUIT)
                .run(() -> this.itemService.findById(id, cantidad),
                        (error) -> metodoAlternativo(id, cantidad, error));
    }

    // Configuracion circuitbreaker automatica con anotacion
    @CircuitBreaker(name = NAME_CIRCUIT, fallbackMethod = "metodoAlternativo")
    @GetMapping("/alternativo/{id}/{cantidad}")
    public Item detalleImplementacionAuto(@PathVariable(name = "id") Long id, @PathVariable(name = "cantidad") Integer cantidad) {
        return this.itemService.findById(id, cantidad);
    }

    // Configuracion circuitbreaker automatica con anotacion
    @TimeLimiter(name = NAME_CIRCUIT, fallbackMethod = "segundoMetodoAlternativo")
    @GetMapping("/timelimiter/{id}/{cantidad}")
    public CompletableFuture<Item> detalleImplementacionTimeLimiter(@PathVariable(name = "id") Long id, @PathVariable(name = "cantidad") Integer cantidad) {
        return CompletableFuture.supplyAsync(() -> this.itemService.findById(id, cantidad));
    }

    @GetMapping("/config")
    public ResponseEntity<?> obtenerConfiguracion() {
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("texto", texto);
        log.info("Texto: {}", texto);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/perfiles")
    public ResponseEntity<?> obtenerPerfilesInfo() {
        Map<String, String> respuesta = new HashMap<>();
        if (environment.getActiveProfiles().length > 0 && environment.getActiveProfiles()[0].equalsIgnoreCase("dev")) {
            String nombre = environment.getProperty("configuracion.autor.nombre");
            String correo = environment.getProperty("configuracion.autor.correo");
            respuesta.put("autor.nombre", nombre);
            respuesta.put("autor.correo", correo);
            log.info("Nombre: {}", nombre);
            log.info("Correo: {}", correo);
        }
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    public Item metodoAlternativo(Long id, Integer cantidad, Throwable error) {
        log.error(error.getMessage());
        return new Item(new ProductoDto(id, "CORTOCIRCUITO", 202.12, LocalDate.now()), cantidad);
    }

    public CompletableFuture<Item> segundoMetodoAlternativo(Long id, Integer cantidad, Throwable error) {
        log.error(error.getMessage());
        return CompletableFuture.supplyAsync(() -> new Item(new ProductoDto(id, "CORTOCIRCUITO", 202.12, LocalDate.now()), cantidad));
    }
}
