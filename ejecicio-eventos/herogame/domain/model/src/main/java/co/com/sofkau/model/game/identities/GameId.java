package co.com.sofkau.model.game.identities;

import co.com.sofkau.model.generic.Identity;

public class GameId extends Identity {
    public GameId() {
    }

    private GameId(String id) {
        super(id);
    }

    public static GameId of(String id) {
        return new GameId(id);
    }
}
