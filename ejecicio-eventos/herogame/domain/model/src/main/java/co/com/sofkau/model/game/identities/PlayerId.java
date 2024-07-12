package co.com.sofkau.model.game.identities;

import co.com.sofkau.model.generic.Identity;

public class PlayerId extends Identity {
    public PlayerId() {
    }

    private PlayerId(String id) {
        super(id);
    }

    public static PlayerId of(String id) {
        return new PlayerId(id);
    }
}