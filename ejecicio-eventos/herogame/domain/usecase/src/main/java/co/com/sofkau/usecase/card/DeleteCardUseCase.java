package co.com.sofkau.usecase.card;

import co.com.sofkau.model.card.Card;
import co.com.sofkau.model.card.gateways.CardRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class DeleteCardUseCase implements Function<String, Mono<Void>> {
    private final CardRepository cardRepository;

    @Override
    public Mono<Void> apply(String id) {
        return this.cardRepository.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Card no encontrada")))
                .map(Card::id)
                .flatMap(this.cardRepository::deleteById);
    }
}
