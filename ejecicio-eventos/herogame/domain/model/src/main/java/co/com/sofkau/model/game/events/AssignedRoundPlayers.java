package co.com.sofkau.model.game.events;

import co.com.sofkau.model.game.identities.PlayerId;
import co.com.sofkau.model.game.identities.RoundId;
import co.com.sofkau.model.generic.DomainEvent;

import java.util.Set;

public class AssignedRoundPlayers extends DomainEvent {
    public static final String EVENT_TYPE = "game.AssignedRoundPlayers";
    private final RoundId roundId;
    private final Set<PlayerId> players;

    public AssignedRoundPlayers(RoundId roundId, Set<PlayerId> players) {
        super(EVENT_TYPE);
        this.roundId = roundId;
        this.players = players;
    }

    public RoundId getRoundId() {
        return roundId;
    }

    public Set<PlayerId> getPlayers() {
        return Set.copyOf(players);
    }
}
