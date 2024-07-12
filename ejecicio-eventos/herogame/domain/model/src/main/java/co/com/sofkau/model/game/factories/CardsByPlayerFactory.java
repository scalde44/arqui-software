package co.com.sofkau.model.game.factories;

import co.com.sofkau.model.game.identities.PlayerId;
import co.com.sofkau.model.game.values.GameCard;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class CardsByPlayerFactory {

    private final Map<PlayerId, Set<GameCard>> cardsByPlayer;
    private static CardsByPlayerFactory instance;

    private CardsByPlayerFactory() {
        cardsByPlayer = new HashMap<>();
    }

    public static CardsByPlayerFactory getInstance() {
        if (Objects.isNull(instance)) {
            instance = new CardsByPlayerFactory();
            return instance;
        }
        return instance;
    }

    public CardsByPlayerFactory add(PlayerId playerId, Set<GameCard> gameCards) {
        cardsByPlayer.put(playerId, gameCards);
        return this;
    }

    public Map<PlayerId, Set<GameCard>> cardsByPlayer() {
        return cardsByPlayer;
    }
}
