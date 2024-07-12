package co.com.sofkau.usecase.card;

import co.com.sofkau.model.card.Card;
import co.com.sofkau.model.card.gateways.CardRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class CreateCardUseCase implements Function<Card, Mono<Card>> {
    private final CardRepository cardRepository;

    @Override
    public Mono<Card> apply(Card card) {
        return this.cardRepository.save(card);
    }
}
