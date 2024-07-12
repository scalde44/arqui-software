package co.com.sofkau.model.game;

import co.com.sofkau.model.game.identities.BoardId;
import co.com.sofkau.model.game.identities.PlayerId;
import co.com.sofkau.model.game.values.GameCard;
import co.com.sofkau.model.game.values.Time;
import co.com.sofkau.model.generic.Entity;

import java.util.HashMap;
import java.util.Map;

public class Board extends Entity<BoardId> {
    private Map<PlayerId, GameCard> cardByPlayer;
    private Time time;
    private Boolean isEnabled;

    public Board(BoardId boardId) {
        super(boardId);
        this.cardByPlayer = new HashMap<>();
        this.time = new Time(Time.DURATION_DEFAULT);
        this.isEnabled = Boolean.TRUE;
    }

    public Map<PlayerId, GameCard> cardByPlayer() {
        return Map.copyOf(cardByPlayer);
    }

    public Boolean existCardByPlayerId(PlayerId playerId) {
        return cardByPlayer.containsKey(playerId);
    }

    public GameCard cardByPlayerId(PlayerId playerId) {
        return Map.copyOf(cardByPlayer).get(playerId);
    }

    public Time time() {
        return time;
    }

    public Boolean isEnabled() {
        return isEnabled;
    }

    public void clearAll() {
        this.cardByPlayer.clear();
        this.isEnabled = Boolean.FALSE;
        this.time = this.time.clear();
    }

    public void addCard(PlayerId playerId, GameCard gameCard) {
        this.cardByPlayer.putIfAbsent(playerId, gameCard);
    }
}
