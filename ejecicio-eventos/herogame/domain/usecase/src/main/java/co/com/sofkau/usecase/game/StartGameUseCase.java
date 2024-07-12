package co.com.sofkau.usecase.game;

import co.com.sofkau.model.game.Game;
import co.com.sofkau.model.game.commands.StartGameCommand;
import co.com.sofkau.model.game.identities.GameId;
import co.com.sofkau.model.generic.DomainEvent;
import co.com.sofkau.model.generic.EventStoreRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@RequiredArgsConstructor
public class StartGameUseCase implements Function<StartGameCommand, Flux<DomainEvent>> {
    private final EventStoreRepository repository;

    @Override
    public Flux<DomainEvent> apply(StartGameCommand command) {
        return this.repository.getEventsBy("game", command.getGameId())
                .collectList()
                .flatMapMany(events -> {
                    var game = Game.from(GameId.of(command.getGameId()), events);
                    if (!game.isPlaying()) {
                        game.startGame();
                    }
                    return Flux.fromIterable(game.getUncommittedChanges());
                });
    }


}
