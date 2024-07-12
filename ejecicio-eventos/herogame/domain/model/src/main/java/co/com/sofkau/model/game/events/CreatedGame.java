package co.com.sofkau.model.game.events;

import co.com.sofkau.model.game.values.DateGame;
import co.com.sofkau.model.generic.DomainEvent;

public class CreatedGame extends DomainEvent {
    public static final String EVENT_TYPE = "game.CreatedGame";
    private final DateGame dateGame;

    public CreatedGame(DateGame dateGame) {
        super(EVENT_TYPE);
        this.dateGame = dateGame;
    }

    public DateGame getDateGame() {
        return dateGame;
    }
}
