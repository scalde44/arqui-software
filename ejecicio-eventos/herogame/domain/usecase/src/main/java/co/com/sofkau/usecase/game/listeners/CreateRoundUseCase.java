package co.com.sofkau.usecase.game.listeners;

import co.com.sofkau.model.game.Game;
import co.com.sofkau.model.game.events.GameStarted;
import co.com.sofkau.model.game.identities.RoundId;
import co.com.sofkau.model.generic.DomainEvent;
import co.com.sofkau.model.generic.EventStoreRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@RequiredArgsConstructor
public class CreateRoundUseCase implements Function<GameStarted, Flux<DomainEvent>> {
    private final EventStoreRepository repository;

    @Override
    public Flux<DomainEvent> apply(GameStarted event) {
        return this.repository.getEventsBy("game", event.getGameId().value())
                .collectList()
                .flatMapMany(events -> {
                    var game = Game.from(event.getGameId(), events);
                    game.createRound(new RoundId());
                    return Flux.fromIterable(game.getUncommittedChanges());
                });
    }
}
