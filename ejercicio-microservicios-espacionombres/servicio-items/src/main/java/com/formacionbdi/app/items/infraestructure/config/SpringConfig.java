package com.formacionbdi.app.items.infraestructure.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class SpringConfig {
    @Bean(name = "clienteRest")
    @LoadBalanced
    public RestTemplate registrarRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
        return (factory) -> factory.configureDefault(id -> {
            return new Resilience4JConfigBuilder(id)
                    .circuitBreakerConfig(CircuitBreakerConfig.custom()
                            .slidingWindowSize(10) // Numero de muestras para comprobar circuito
                            .failureRateThreshold(50) // Porcentaje de fallo sobre la muestra para abrir circuito
                            .waitDurationInOpenState(Duration.ofSeconds(10)) // Duracion en circuito abierto
                            .permittedNumberOfCallsInHalfOpenState(5) // Numero de llamadas permitidas en circuito semi-abierto
                            .slowCallRateThreshold(50) // Porcentaje de tolerancia para abrir circuito por llamadas lentas
                            .slowCallDurationThreshold(Duration.ofSeconds(2L)) // Duracion limite para catalogar una llamada como lenta
                            .build())
                    .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(6L)).build()) // Tiempo limite de peticion para abrir circuito
                    .build();
        });
    }
}
