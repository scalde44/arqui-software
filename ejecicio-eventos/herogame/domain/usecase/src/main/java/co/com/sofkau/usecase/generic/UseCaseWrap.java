package co.com.sofkau.usecase.generic;

import co.com.sofkau.model.generic.DomainEvent;
import reactor.core.publisher.Flux;

import java.util.function.Function;

public class UseCaseWrap<T extends DomainEvent> {
    private final Function<T, Flux<DomainEvent>> usecase;
    private final String eventType;

    public UseCaseWrap(Function<T, Flux<DomainEvent>> usecase, String eventType) {
        this.usecase = usecase;
        this.eventType = eventType;
    }

    public Flux<DomainEvent> executeUseCase(DomainEvent domainEvent) {
        return this.usecase.apply((T) domainEvent);
    }

    public String getEventType() {
        return eventType;
    }
}
