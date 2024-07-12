package co.com.sofkau.usecase.game.listeners;

import co.com.sofkau.model.game.Game;
import co.com.sofkau.model.game.events.RemovedLosingCards;
import co.com.sofkau.model.game.identities.RoundId;
import co.com.sofkau.model.generic.DomainEvent;
import co.com.sofkau.model.generic.EventStoreRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class ValidateGameWinnerUseCase implements Function<RemovedLosingCards, Flux<DomainEvent>> {
    private final EventStoreRepository repository;

    @Override
    public Flux<DomainEvent> apply(RemovedLosingCards event) {
        return this.repository.getEventsBy("game", event.getGameId().value())
                .collectList()
                .flatMapMany(events -> {
                    var game = Game.from(event.getGameId(), events);
                    var playersAvailable = game.players().stream()
                            .filter(player -> player.gameCards().size() > 0)
                            .collect(Collectors.toSet());
                    if (playersAvailable.size() > 1) {
                        game.emptyBoard();
                        game.createRound(new RoundId());
                    } else {
                        game.finishGame(playersAvailable.stream().findFirst().get());
                    }
                    return Flux.fromIterable(game.getUncommittedChanges());
                });
    }
}
