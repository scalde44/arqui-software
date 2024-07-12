package co.com.sofkau.api.helper;

import co.com.sofkau.model.generic.DomainEvent;
import co.com.sofkau.model.generic.EventBus;
import co.com.sofkau.model.generic.EventStoreRepository;
import co.com.sofkau.model.generic.StoredEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class HandlerBase {
    private final EventStoreRepository repository;
    private final StoredEvent.EventSerializer eventSerializer;
    private final EventBus eventBus;

    public Mono<Void> emit(Mono<DomainEvent> events) {
        return events
                .flatMap(domainEvent ->
                        Mono.defer(() -> this.repository.saveEvent(
                                        domainEvent.getAggregateName(),
                                        domainEvent.aggregateRootId(),
                                        StoredEvent.wrapEvent(domainEvent, eventSerializer)
                                ).mergeWith(Mono.defer(() -> this.eventBus.publish(domainEvent))).then()
                        )
                );
    }
}
