package co.com.sofkau.model.game.events;

import co.com.sofkau.model.game.identities.GameId;
import co.com.sofkau.model.game.identities.PlayerId;
import co.com.sofkau.model.game.identities.RoundId;
import co.com.sofkau.model.generic.DomainEvent;

public class RemovedLosingCards extends DomainEvent {
    public static final String EVENT_TYPE = "game.RemovedLosingCards";
    private final GameId gameId;
    private final PlayerId winnerId;
    private final PlayerId loserId;
    private final RoundId roundId;

    public RemovedLosingCards(GameId gameId, PlayerId winnerId, PlayerId loserId, RoundId roundId) {
        super(EVENT_TYPE);
        this.gameId = gameId;
        this.winnerId = winnerId;
        this.loserId = loserId;
        this.roundId = roundId;
    }

    public GameId getGameId() {
        return gameId;
    }

    public PlayerId getWinnerId() {
        return winnerId;
    }

    public PlayerId getLoserId() {
        return loserId;
    }

    public RoundId getRoundId() {
        return roundId;
    }
}
