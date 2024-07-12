package co.com.sofkau.model.game.events;

import co.com.sofkau.model.game.identities.GameId;
import co.com.sofkau.model.generic.DomainEvent;

public class GameStarted extends DomainEvent {
    public static final String EVENT_TYPE = "game.GameStarted";
    private final GameId gameId;

    public GameStarted(GameId gameId) {
        super(EVENT_TYPE);
        this.gameId = gameId;
    }

    public GameId getGameId() {
        return gameId;
    }
}
