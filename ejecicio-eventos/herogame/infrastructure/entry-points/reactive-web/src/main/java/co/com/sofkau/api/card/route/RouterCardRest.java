package co.com.sofkau.api.card.route;

import co.com.sofkau.api.card.handler.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
public class RouterCardRest {
    @Bean
    public RouterFunction<ServerResponse> routerFindAllCardsFunction(FindAllCardsHandler handler) {
        return route(GET("/api/v1/cards"), handler::findAll);
    }

    @Bean
    public RouterFunction<ServerResponse> routerCreateCardFunction(CreateCardHandler handler) {
        return route(POST("/api/v1/cards"), handler::create);
    }

    @Bean
    public RouterFunction<ServerResponse> routerUpdatePowerCardFunction(UpdateCardHandler handler) {
        return route(PUT("/api/v1/cards/{id}"), handler::update);
    }

    @Bean
    public RouterFunction<ServerResponse> routerDeleteCardFunction(DeleteCardHandler handler) {
        return route(DELETE("/api/v1/cards/{id}"), handler::delete);
    }
    @Bean
    public RouterFunction<ServerResponse> routerCardByIdFunction(GetCardByIdHandler handler){
        return route(GET("/api/v1/cards/{id}"), handler::cardById);
    }
}
