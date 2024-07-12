package co.com.sofkau.model.game.identities;

import co.com.sofkau.model.generic.Identity;

public class BoardId extends Identity {
    public BoardId() {
    }

    private BoardId(String id) {
        super(id);
    }

    public static BoardId of(String id) {
        return new BoardId(id);
    }
}