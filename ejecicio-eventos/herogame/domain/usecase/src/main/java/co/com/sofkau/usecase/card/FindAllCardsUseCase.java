package co.com.sofkau.usecase.card;

import co.com.sofkau.model.card.Card;
import co.com.sofkau.model.card.gateways.CardRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class FindAllCardsUseCase implements Supplier<Flux<Card>> {
    private final CardRepository cardRepository;

    @Override
    public Flux<Card> get() {
        return this.cardRepository.findAll();
    }
}
