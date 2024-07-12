package co.com.sofkau.api.game.route;

import co.com.sofkau.api.game.handler.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterGameRest {

    @Bean
    public RouterFunction<ServerResponse> routerCreateGameFunction(CreateGameHandler handler) {
        return route(POST("/api/v1/game/create"), handler::create);
    }

    @Bean
    public RouterFunction<ServerResponse> routerAddCardToBoardFunction(AddCardToBoardHandler handler) {
        return route(POST("/api/v1/game/board/card"), handler::add);
    }

    @Bean
    public RouterFunction<ServerResponse> routerStartGameFunction(StartGameHandler handler) {
        return route(POST("/api/v1/game/start"), handler::start);
    }

    @Bean
    public RouterFunction<ServerResponse> routerFinishRoundFunction(FinishRoundHandler handler) {
        return route(POST("/api/v1/game/round/finish"), handler::finish);
    }

    @Bean
    public RouterFunction<ServerResponse> routerGetEventsFunction(GetEventsHandler handler) {
        return route(GET("/api/v1/game/events/{gameId}"), handler::getEvents);
    }

    @Bean
    public RouterFunction<ServerResponse> routerGetGamePlayerFunction(GetGamePlayerHandler handler) {
        return route(GET("/api/v1/game/{gameId}/players/{userId}"), handler::findPlayerByGameIdAndUserId);
    }
}