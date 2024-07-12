package co.com.sofkau.usecase.game.listeners;

import co.com.sofkau.model.card.Card;
import co.com.sofkau.model.card.gateways.CardRepository;
import co.com.sofkau.model.game.Game;
import co.com.sofkau.model.game.events.GameStarted;
import co.com.sofkau.model.game.factories.CardsByPlayerFactory;
import co.com.sofkau.model.game.identities.GameId;
import co.com.sofkau.model.game.values.GameCard;
import co.com.sofkau.model.generic.DomainEvent;
import co.com.sofkau.model.generic.EventStoreRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class DistributeCardsUseCase implements Function<GameStarted, Flux<DomainEvent>> {
    private final EventStoreRepository repository;
    private final CardRepository cardRepository;

    @Override
    public Flux<DomainEvent> apply(GameStarted event) {
        return this.cardRepository.findAll()
                .collectList()
                .zipWith(this.repository.getEventsBy("game", event.getGameId().value()).collectList())
                .flatMapMany(objects -> {
                    var cards = objects.getT1();
                    var events = objects.getT2();
                    var game = Game.from(event.getGameId(), events);
                    return Flux.fromIterable(game.players())
                            .map(player -> CardsByPlayerFactory.getInstance().add(
                                            player.identity(),
                                            generateCards(cards)
                                    )
                            )
                            .last()
                            .map(factories -> {
                                game.distributeCards(factories);
                                return game.getUncommittedChanges();
                            })
                            .flatMapIterable(events1 -> events1);
                });
    }
    private Set<GameCard> generateCards(List<Card> cards) {
        Collections.shuffle(cards);
        return Flux.fromIterable(cards)
                .take(5)
                .map(card -> new GameCard(card, Boolean.FALSE))
                .collect(Collectors.toSet())
                .block();
    }
}