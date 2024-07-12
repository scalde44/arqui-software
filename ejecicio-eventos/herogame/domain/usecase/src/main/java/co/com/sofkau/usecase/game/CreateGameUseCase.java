package co.com.sofkau.usecase.game;

import co.com.sofkau.model.game.Game;
import co.com.sofkau.model.game.commands.CreateGameCommand;
import co.com.sofkau.model.game.factories.PlayerFactory;
import co.com.sofkau.model.game.identities.GameId;
import co.com.sofkau.model.game.identities.PlayerId;
import co.com.sofkau.model.game.values.DateGame;
import co.com.sofkau.model.generic.DomainEvent;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.function.Function;

public class CreateGameUseCase implements Function<CreateGameCommand, Flux<DomainEvent>> {
    @Override
    public Flux<DomainEvent> apply(CreateGameCommand command) {
        var game = new Game(
                GameId.of(command.getGameId()),
                command.getUsersId().stream()
                        .map(userId -> PlayerFactory.getInstance().add(new PlayerId(), userId))
                        .reduce((first, second) -> second)
                        .get(),
                new DateGame(LocalDate.now())
        );
        return Flux.fromIterable(game.getUncommittedChanges());
    }
}
