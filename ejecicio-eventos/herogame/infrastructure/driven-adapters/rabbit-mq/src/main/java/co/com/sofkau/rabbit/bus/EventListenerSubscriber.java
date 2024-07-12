package co.com.sofkau.rabbit.bus;

import co.com.sofkau.model.generic.DomainEvent;
import co.com.sofkau.model.generic.EventBus;
import co.com.sofkau.model.generic.EventStoreRepository;
import co.com.sofkau.model.generic.StoredEvent;
import co.com.sofkau.usecase.generic.UseCaseWrap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class EventListenerSubscriber {
    private final EventStoreRepository repository;
    private final Set<UseCaseWrap> useCases;
    private final StoredEvent.EventSerializer eventSerializer;
    private final EventBus eventBus;


    public void onNext(DomainEvent domainEvent) {
        this.useCases.stream()
                .filter(useCaseWrap -> useCaseWrap.getEventType().equals(domainEvent.type))
                .forEach(useCaseWrap -> {
                    var events = (List<DomainEvent>) useCaseWrap.executeUseCase(domainEvent).collectList().block();
                    events.forEach(event -> {
                        this.repository.saveEvent(
                                        event.getAggregateName(),
                                        event.aggregateRootId(),
                                        StoredEvent.wrapEvent(event, eventSerializer))
                                .mergeWith(this.eventBus.publish(event)).then().block();
                    });
                });

    }
}
