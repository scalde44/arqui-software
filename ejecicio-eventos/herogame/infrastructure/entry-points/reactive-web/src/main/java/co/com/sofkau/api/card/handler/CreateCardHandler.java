package co.com.sofkau.api.card.handler;

import co.com.sofkau.api.card.dto.CardDTO;
import co.com.sofkau.api.card.helper.MapperCard;
import co.com.sofkau.usecase.card.CreateCardUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CreateCardHandler {
    private final CreateCardUseCase useCase;
    private final MapperCard mapperCard;

    public Mono<ServerResponse> create(ServerRequest serverRequest) {
        return serverRequest
                .bodyToMono(CardDTO.class)
                .map(cardDTO -> this.mapperCard.mapToModel(null).apply(cardDTO))
                .flatMap(this.useCase::apply)
                .map(this.mapperCard.mapToDTO())
                .flatMap(card -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(card)
                );
    }
}
