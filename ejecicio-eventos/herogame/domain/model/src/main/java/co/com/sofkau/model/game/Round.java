package co.com.sofkau.model.game;

import co.com.sofkau.model.game.identities.PlayerId;
import co.com.sofkau.model.game.identities.RoundId;
import co.com.sofkau.model.game.values.RoundNumber;
import co.com.sofkau.model.generic.Entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Round extends Entity<RoundId> {

    private Set<PlayerId> playerIds;
    private RoundNumber roundNumber;
    private Boolean isFinished;
    private PlayerId winner;

    public Round(RoundId entityId, RoundNumber roundNumber) {
        super(entityId);
        this.roundNumber = roundNumber;
        this.playerIds = new HashSet<>();
        this.isFinished = Boolean.FALSE;
    }

    public Set<PlayerId> playerIds() {
        return playerIds;
    }

    public Integer roundNumber() {
        return roundNumber.value();
    }

    public Boolean isFinished() {
        return isFinished;
    }

    public void finish(PlayerId winner) {
        this.isFinished = Boolean.TRUE;
        this.winner = winner;
    }

    public void addPlayer(PlayerId playerId) {
        this.playerIds.add(Objects.requireNonNull(playerId));
    }

}
