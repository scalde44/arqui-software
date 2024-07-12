package co.com.sofkau.api.card.handler;

import co.com.sofkau.api.card.dto.CardDTO;
import co.com.sofkau.api.card.helper.MapperCard;
import co.com.sofkau.usecase.card.FindAllCardsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class FindAllCardsHandler {
    private final FindAllCardsUseCase useCase;
    private final MapperCard mapperCard;

    public Mono<ServerResponse> findAll(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(this.useCase.get()
                        .map(this.mapperCard.mapToDTO()), CardDTO.class));
    }
}
