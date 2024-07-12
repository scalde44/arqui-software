package co.com.sofkau.api.card.handler;

import co.com.sofkau.api.card.helper.MapperCard;
import co.com.sofkau.usecase.card.DeleteCardUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class DeleteCardHandler {
    private final DeleteCardUseCase useCase;
    private final MapperCard mapperCard;

    public Mono<ServerResponse> delete(ServerRequest serverRequest) {
        return Mono.just(
                        serverRequest.pathVariable("id")
                )
                .flatMap(useCase::apply)
                .flatMap(card -> ServerResponse
                        .status(HttpStatus.NO_CONTENT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(card)
                );
    }
}
