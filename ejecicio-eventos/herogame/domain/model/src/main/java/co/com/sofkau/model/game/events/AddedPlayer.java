package co.com.sofkau.model.game.events;

import co.com.sofkau.model.game.identities.PlayerId;
import co.com.sofkau.model.generic.DomainEvent;

public class AddedPlayer extends DomainEvent {
    public static final String EVENT_TYPE = "game.AddedPlayer";
    private final PlayerId identity;
    private final String userId;

    public AddedPlayer(PlayerId identity, String userId) {
        super(EVENT_TYPE);
        this.identity = identity;
        this.userId = userId;
    }

    public PlayerId getIdentity() {
        return identity;
    }

    public String getUserId() {
        return userId;
    }
}
