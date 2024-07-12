package co.com.sofkau.model.game.factories;

import co.com.sofkau.model.game.Player;
import co.com.sofkau.model.game.identities.PlayerId;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class PlayerFactory {

    private final Set<Player> players;
    private static PlayerFactory instance;

    private PlayerFactory() {
        players = new HashSet<>();
    }

    public static PlayerFactory getInstance() {
        if (Objects.isNull(instance)) {
            instance = new PlayerFactory();
            return instance;
        }
        return instance;
    }

    public PlayerFactory add(PlayerId playerId, String userId) {
        players.add(new Player(playerId, userId));
        return this;
    }

    public Set<Player> players() {
        return players;
    }
}