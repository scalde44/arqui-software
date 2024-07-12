package co.com.sofkau.usecase.card;

import co.com.sofkau.model.card.Card;
import co.com.sofkau.model.card.gateways.CardRepository;
import co.com.sofkau.model.card.values.Image;
import co.com.sofkau.model.card.values.Name;
import co.com.sofkau.model.card.values.Power;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class UpdateCardUseCase implements BiFunction<Card, String, Mono<Card>> {
    private final CardRepository cardRepository;

    @Override
    public Mono<Card> apply(Card card, String id) {
        return this.cardRepository.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Card no encontrada")))
                .map(c -> {
                    c.updateName(new Name(card.name()));
                    c.updatePower(new Power(card.power()));
                    c.updateImage(new Image(card.imageUrl()));
                    c.updateFeatures(card.features());
                    return c;
                })
                .flatMap(this.cardRepository::save);
    }
}
