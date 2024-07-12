package co.com.sofkau.api.game.handler;

import co.com.sofkau.api.helper.HandlerBase;
import co.com.sofkau.model.game.commands.AddCardToBoardCommand;
import co.com.sofkau.model.game.commands.StartGameCommand;
import co.com.sofkau.model.generic.EventBus;
import co.com.sofkau.model.generic.EventStoreRepository;
import co.com.sofkau.model.generic.StoredEvent;
import co.com.sofkau.usecase.game.AddCardToBoardUseCase;
import co.com.sofkau.usecase.game.StartGameUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class StartGameHandler extends HandlerBase {
    private final StartGameUseCase useCase;

    public StartGameHandler(EventStoreRepository repository, StoredEvent.EventSerializer eventSerializer, EventBus eventBus, StartGameUseCase useCase) {
        super(repository, eventSerializer, eventBus);
        this.useCase = useCase;
    }


    public Mono<ServerResponse> start(ServerRequest serverRequest) {
        return serverRequest
                .bodyToMono(StartGameCommand.class)
                .flatMapMany(this.useCase::apply)
                .flatMap(domainEvent -> emit(Mono.just(domainEvent)))
                .then(ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .build());
    }
}
