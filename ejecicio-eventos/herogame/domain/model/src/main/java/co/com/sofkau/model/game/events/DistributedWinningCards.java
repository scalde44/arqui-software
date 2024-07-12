package co.com.sofkau.model.game.events;

import co.com.sofkau.model.game.identities.GameId;
import co.com.sofkau.model.game.identities.PlayerId;
import co.com.sofkau.model.game.identities.RoundId;
import co.com.sofkau.model.generic.DomainEvent;

public class DistributedWinningCards extends DomainEvent {
    public static final String EVENT_TYPE = "game.DistributedWinningCards";
    private final GameId gameId;
    private final PlayerId winnerId;
    private final RoundId roundId;

    public DistributedWinningCards(GameId gameId, PlayerId winnerId, RoundId roundId) {
        super(EVENT_TYPE);
        this.gameId = gameId;
        this.winnerId = winnerId;
        this.roundId = roundId;
    }

    public GameId getGameId() {
        return gameId;
    }

    public PlayerId getWinnerId() {
        return winnerId;
    }

    public RoundId getRoundId() {
        return roundId;
    }
}
