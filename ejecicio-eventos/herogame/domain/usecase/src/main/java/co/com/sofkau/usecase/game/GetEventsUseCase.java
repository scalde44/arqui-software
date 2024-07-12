package co.com.sofkau.usecase.game;

import co.com.sofkau.model.generic.DomainEvent;
import co.com.sofkau.model.generic.EventStoreRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import java.util.function.Function;


@RequiredArgsConstructor
public class GetEventsUseCase implements Function<String, Flux<DomainEvent>> {

    private final EventStoreRepository repository;

    @Override
    public Flux<DomainEvent> apply(String aggregateRootId) {
                return this.repository.getEventsBy("game", aggregateRootId);
    }
}
