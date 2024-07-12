package co.com.sofkau.model.game.events;

import co.com.sofkau.model.game.identities.PlayerId;
import co.com.sofkau.model.game.values.GameCard;
import co.com.sofkau.model.generic.DomainEvent;

import java.util.Set;

public class DistributedCards extends DomainEvent {
    public static final String EVENT_TYPE = "game.DistributedCards";
    private final PlayerId playerId;
    private final Set<GameCard> gameCards;

    public DistributedCards(PlayerId playerId, Set<GameCard> gameCards) {
        super(EVENT_TYPE);
        this.playerId = playerId;
        this.gameCards = gameCards;
    }

    public PlayerId getPlayerId() {
        return playerId;
    }

    public Set<GameCard> getGameCards() {
        return gameCards;
    }
}
