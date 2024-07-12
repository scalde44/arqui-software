package co.com.sofkau.usecase.game.listeners;

import co.com.sofkau.model.game.Game;
import co.com.sofkau.model.game.events.DistributedWinningCards;
import co.com.sofkau.model.generic.DomainEvent;
import co.com.sofkau.model.generic.EventStoreRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@RequiredArgsConstructor
public class RemoveLosingCardsUseCase implements Function<DistributedWinningCards, Flux<DomainEvent>> {
    private final EventStoreRepository repository;

    @Override
    public Flux<DomainEvent> apply(DistributedWinningCards event) {
        return this.repository.getEventsBy("game", event.getGameId().value())
                .collectList()
                .flatMapMany(events -> {
                    var game = Game.from(event.getGameId(), events);
                    game.removeLosingCards(event.getWinnerId(), event.getRoundId());
                    return Flux.fromIterable(game.getUncommittedChanges());
                });
    }
}