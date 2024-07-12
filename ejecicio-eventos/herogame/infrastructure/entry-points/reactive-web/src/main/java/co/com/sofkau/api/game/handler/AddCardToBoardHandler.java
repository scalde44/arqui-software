package co.com.sofkau.api.game.handler;

import co.com.sofkau.api.helper.HandlerBase;
import co.com.sofkau.model.game.commands.AddCardToBoardCommand;
import co.com.sofkau.model.generic.EventBus;
import co.com.sofkau.model.generic.EventStoreRepository;
import co.com.sofkau.model.generic.StoredEvent;
import co.com.sofkau.usecase.game.AddCardToBoardUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class AddCardToBoardHandler extends HandlerBase {
    private final AddCardToBoardUseCase useCase;

    public AddCardToBoardHandler(EventStoreRepository repository, StoredEvent.EventSerializer eventSerializer, EventBus eventBus, AddCardToBoardUseCase useCase) {
        super(repository, eventSerializer, eventBus);
        this.useCase = useCase;
    }


    public Mono<ServerResponse> add(ServerRequest serverRequest) {
        return serverRequest
                .bodyToMono(AddCardToBoardCommand.class)
                .flatMapMany(this.useCase::apply)
                .flatMap(domainEvent -> emit(Mono.just(domainEvent)))
                .then(ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .build());
    }
}