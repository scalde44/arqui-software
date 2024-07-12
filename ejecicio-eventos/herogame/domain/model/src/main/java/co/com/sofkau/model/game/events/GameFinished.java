package co.com.sofkau.model.game.events;

import co.com.sofkau.model.game.Player;
import co.com.sofkau.model.game.identities.GameId;
import co.com.sofkau.model.generic.DomainEvent;

public class GameFinished extends DomainEvent {
    public static final String EVENT_TYPE = "game.GameFinished";
    private final GameId gameId;
    private final Player winner;

    public GameFinished(GameId gameId, Player winner) {
        super(EVENT_TYPE);
        this.gameId = gameId;
        this.winner = winner;
    }

    public GameId getGameId() {
        return gameId;
    }

    public Player getWinner() {
        return winner;
    }
}
