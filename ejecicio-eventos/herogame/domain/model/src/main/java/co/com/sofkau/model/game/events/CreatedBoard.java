package co.com.sofkau.model.game.events;

import co.com.sofkau.model.game.identities.BoardId;
import co.com.sofkau.model.generic.DomainEvent;

public class CreatedBoard extends DomainEvent {
    public static final String EVENT_TYPE = "game.CreatedBoard";
    private final BoardId boardId;

    public CreatedBoard(BoardId boardId) {
        super(EVENT_TYPE);
        this.boardId = boardId;
    }

    public BoardId getBoardId() {
        return boardId;
    }
}
