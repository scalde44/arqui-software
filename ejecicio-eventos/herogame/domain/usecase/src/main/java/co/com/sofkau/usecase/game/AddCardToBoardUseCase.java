package co.com.sofkau.usecase.game;

import co.com.sofkau.model.card.gateways.CardRepository;
import co.com.sofkau.model.game.Game;
import co.com.sofkau.model.game.commands.AddCardToBoardCommand;
import co.com.sofkau.model.game.identities.GameId;
import co.com.sofkau.model.game.identities.PlayerId;
import co.com.sofkau.model.game.values.GameCard;
import co.com.sofkau.model.generic.DomainEvent;
import co.com.sofkau.model.generic.EventStoreRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@RequiredArgsConstructor
public class AddCardToBoardUseCase implements Function<AddCardToBoardCommand, Flux<DomainEvent>> {
    private final EventStoreRepository repository;
    private final CardRepository cardRepository;

    @Override
    public Flux<DomainEvent> apply(AddCardToBoardCommand command) {
        return this.repository.getEventsBy("game", command.getGameId()).collectList()
                .zipWith(this.cardRepository.findById(command.getCardId()))
                .flatMapIterable((objects) -> {
                    var events = objects.getT1();
                    var card = objects.getT2();
                    var game = Game.from(GameId.of(command.getGameId()), events);

                    game.addCardToBoard(PlayerId.of(command.getPlayerId()), new GameCard(card, Boolean.TRUE));
                    return game.getUncommittedChanges();
                });
    }
}
