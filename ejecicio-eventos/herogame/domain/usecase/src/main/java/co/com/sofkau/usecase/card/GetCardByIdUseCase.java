package co.com.sofkau.usecase.card;

import co.com.sofkau.model.card.Card;
import co.com.sofkau.model.card.gateways.CardRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GetCardByIdUseCase {
    private final CardRepository cardRepository;

    public Mono<Card> cardById(String id) {
        return cardRepository.findById(id);
    }
}
