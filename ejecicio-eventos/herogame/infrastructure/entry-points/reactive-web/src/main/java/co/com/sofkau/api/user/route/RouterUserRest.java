package co.com.sofkau.api.user.route;


import co.com.sofkau.api.user.handler.CreateUserHandler;
import co.com.sofkau.api.user.handler.FindAllUsersHandler;
import co.com.sofkau.api.user.handler.FindByEmailUserHandler;
import co.com.sofkau.api.user.handler.UpdateUserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterUserRest {
    @Bean
    public RouterFunction<ServerResponse> routerFindAllUsersFunction(FindAllUsersHandler handler) {
        return route(GET("/api/v1/users"), handler::findAll);
    }

    @Bean
    public RouterFunction<ServerResponse> routerCreateUserFunction(CreateUserHandler handler) {
        return route(POST("/api/v1/users"), handler::create);
    }

    @Bean
    public RouterFunction<ServerResponse> routerUpdateUserFunction(UpdateUserHandler handler) {
        return route(PUT("/api/v1/users/{id}"), handler::update);
    }

    @Bean
    public RouterFunction<ServerResponse> routerFindByEmialUserFunction(FindByEmailUserHandler handler) {
        return route(GET("/api/v1/user/{email}"), handler::findByEmail);
    }


}
