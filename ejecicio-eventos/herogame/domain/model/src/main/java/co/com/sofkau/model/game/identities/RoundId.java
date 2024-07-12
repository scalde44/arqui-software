package co.com.sofkau.model.game.identities;

import co.com.sofkau.model.generic.Identity;

public class RoundId extends Identity {
    public RoundId() {
    }

    private RoundId(String id) {
        super(id);
    }

    public static RoundId of(String id) {
        return new RoundId(id);
    }
}
