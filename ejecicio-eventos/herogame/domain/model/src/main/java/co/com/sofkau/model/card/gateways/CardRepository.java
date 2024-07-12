package co.com.sofkau.model.card.gateways;

import co.com.sofkau.model.card.Card;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CardRepository {
    Mono<Card> save(Card card);

    Mono<Card> findById(String id);

    Mono<Void> deleteById(String id);

    Flux<Card> findAll();
}
