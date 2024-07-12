package co.com.sofkau.model.game.events;

import co.com.sofkau.model.game.identities.GameId;
import co.com.sofkau.model.game.identities.RoundId;
import co.com.sofkau.model.game.values.RoundNumber;
import co.com.sofkau.model.generic.DomainEvent;

public class CreatedRound extends DomainEvent {
    public static final String EVENT_TYPE = "game.CreatedRound";
    private final GameId gameId;
    private final RoundId roundId;
    private final RoundNumber roundNumber;

    public CreatedRound(GameId gameId,RoundId roundId, RoundNumber roundNumber) {
        super(EVENT_TYPE);
        this.gameId = gameId;
        this.roundId = roundId;
        this.roundNumber = roundNumber;
    }

    public GameId getGameId() {
        return gameId;
    }

    public RoundId getRoundId() {
        return roundId;
    }

    public RoundNumber getRoundNumber() {
        return roundNumber;
    }
}
