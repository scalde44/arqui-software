package co.com.sofkau.model.game.events;

import co.com.sofkau.model.game.identities.GameId;
import co.com.sofkau.model.game.identities.PlayerId;
import co.com.sofkau.model.game.identities.RoundId;
import co.com.sofkau.model.generic.DomainEvent;

public class FinishedRound extends DomainEvent {
    public static final String EVENT_TYPE = "game.FinishedRound";
    private final GameId gameId;
    private final RoundId roundId;
    private final PlayerId winner;

    public FinishedRound(GameId gameId,RoundId roundId, PlayerId winner) {
        super(EVENT_TYPE);
        this.gameId = gameId;
        this.roundId = roundId;
        this.winner = winner;
    }

    public GameId getGameId() {
        return gameId;
    }

    public RoundId getRoundId() {
        return roundId;
    }

    public PlayerId getWinner() {
        return winner;
    }
}
