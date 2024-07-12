package co.com.sofkau.api.card.handler;

import co.com.sofkau.api.card.dto.CardDTO;
import co.com.sofkau.api.card.helper.MapperCard;
import co.com.sofkau.usecase.card.UpdateCardUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UpdateCardHandler {
    private final UpdateCardUseCase useCase;
    private final MapperCard mapperCard;

    public Mono<ServerResponse> update(ServerRequest serverRequest) {
        return serverRequest
                .bodyToMono(CardDTO.class)
                .zipWith(Mono.just(serverRequest.pathVariable("id")))
                .map(objects -> this.mapperCard.mapToModel(objects.getT2()).apply(objects.getT1()))
                .flatMap(card -> this.useCase.apply(card, card.id()))
                .map(this.mapperCard.mapToDTO())
                .flatMap(card -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(card)
                );
    }
}
