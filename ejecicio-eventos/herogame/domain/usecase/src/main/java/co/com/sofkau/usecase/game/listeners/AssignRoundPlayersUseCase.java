package co.com.sofkau.usecase.game.listeners;

import co.com.sofkau.model.game.Game;
import co.com.sofkau.model.game.events.CreatedRound;
import co.com.sofkau.model.generic.DomainEvent;
import co.com.sofkau.model.generic.EventStoreRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@RequiredArgsConstructor
public class AssignRoundPlayersUseCase implements Function<CreatedRound, Flux<DomainEvent>> {
    private final EventStoreRepository repository;

    @Override
    public Flux<DomainEvent> apply(CreatedRound event) {
        return this.repository.getEventsBy("game", event.getGameId().value())
                .collectList()
                .flatMapMany(events -> {
                    var game = Game.from(event.getGameId(), events);
                    game.assignRoundPlayers(event.getRoundId());
                    return Flux.fromIterable(game.getUncommittedChanges());
                });
    }
}