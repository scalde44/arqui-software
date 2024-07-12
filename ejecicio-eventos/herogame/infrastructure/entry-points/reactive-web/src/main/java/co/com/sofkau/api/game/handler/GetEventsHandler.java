package co.com.sofkau.api.game.handler;

import co.com.sofkau.api.generic.SuccessNotification;
import co.com.sofkau.api.generic.SuccessNotificationSerializer;
import co.com.sofkau.usecase.game.GetEventsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class GetEventsHandler {

    private final GetEventsUseCase useCase;

    public Mono<ServerResponse> getEvents(ServerRequest serverRequest) {

        return this.useCase.apply(serverRequest.pathVariable("gameId"))
                .map(domainEvent -> SuccessNotification.wrapEvent(" ", domainEvent))
                .map(successNotification -> SuccessNotificationSerializer.instance().serialize(successNotification))
                .collectList()
                .flatMap(json -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(json));
    }
}
