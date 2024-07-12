package com.formacionbdi.app.gateway.filters.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class ExampleGatewayFilterFactory extends AbstractGatewayFilterFactory<ExampleGatewayFilterFactory.SpringConfig> {
    private static final Logger log = LoggerFactory.getLogger(ExampleGatewayFilterFactory.class);
    private static final String NOMBRE_GATEWAY = "EjemploCookie";

    public ExampleGatewayFilterFactory() {
        super(SpringConfig.class);
    }

    @Override
    public GatewayFilter apply(SpringConfig config) {
        return (exchange, chain) -> {
            log.info("Ejecutando pre gateway filter factory: " + config.getMensaje());
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("Ejecutando post gateway filter factory: " + config.getMensaje());
                Optional.ofNullable(config.getCookieValor()).ifPresent(cookie -> {
                    exchange.getResponse().addCookie(ResponseCookie.from(config.getCookieLlave(), config.getCookieValor()).build());
                });
            }));
        };
    }

    @Override
    public String name() {
        return NOMBRE_GATEWAY;
    }

    protected static class SpringConfig {
        private String mensaje;
        private String cookieValor;
        private String cookieLlave;

        public String getMensaje() {
            return mensaje;
        }

        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }

        public String getCookieValor() {
            return cookieValor;
        }

        public void setCookieValor(String cookieValor) {
            this.cookieValor = cookieValor;
        }

        public String getCookieLlave() {
            return cookieLlave;
        }

        public void setCookieLlave(String cookieLlave) {
            this.cookieLlave = cookieLlave;
        }
    }
}
