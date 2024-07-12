package co.com.sofkau.usecase.game.listeners;

import co.com.sofkau.model.game.Game;
import co.com.sofkau.model.game.events.FinishedRound;
import co.com.sofkau.model.generic.DomainEvent;
import co.com.sofkau.model.generic.EventStoreRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@RequiredArgsConstructor
public class DistributeWinningCardsUseCase implements Function<FinishedRound, Flux<DomainEvent>> {
    private final EventStoreRepository repository;

    @Override
    public Flux<DomainEvent> apply(FinishedRound event) {
        return this.repository.getEventsBy("game", event.getGameId().value())
                .collectList()
                .flatMapMany(events -> {
                    var game = Game.from(event.getGameId(), events);
                    game.distributeWinningCards(event.getWinner(), event.getRoundId());
                    return Flux.fromIterable(game.getUncommittedChanges());
                });
    }
}
