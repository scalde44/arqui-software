package co.com.sofkau.api.game.handler;

import co.com.sofkau.api.game.helper.MapperGamePlayer;
import co.com.sofkau.model.game.identities.GameId;
import co.com.sofkau.model.game.queries.GamePlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class GetGamePlayerHandler {

    private final GamePlayerService service;

    private final MapperGamePlayer mapperGamePlayer;

    public Mono<ServerResponse> findPlayerByGameIdAndUserId(ServerRequest serverRequest) {
        return Mono.just(serverRequest.pathVariable("gameId"))
                .zipWith(Mono.just(serverRequest.pathVariable("userId")))
                .flatMap(objects ->
                        this.service.getGamePlayerBy(GameId.of(objects.getT1()), objects.getT2())
                )
                .map(this.mapperGamePlayer.mapToDTO())
                .flatMap(dto -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(dto));
    }

}