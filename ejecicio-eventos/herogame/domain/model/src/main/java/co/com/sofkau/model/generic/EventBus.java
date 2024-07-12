package co.com.sofkau.model.generic;

import reactor.core.publisher.Mono;

public interface EventBus {

     Mono<Void> publish(DomainEvent event) ;

     Mono<Void> publishError(Throwable errorEvent);
}