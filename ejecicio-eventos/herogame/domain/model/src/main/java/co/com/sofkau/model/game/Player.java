package co.com.sofkau.model.game;

import co.com.sofkau.model.game.identities.PlayerId;
import co.com.sofkau.model.game.values.GameCard;
import co.com.sofkau.model.game.values.Score;
import co.com.sofkau.model.generic.Entity;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Player extends Entity<PlayerId> {

    private Score score;
    private String userId;
    private Set<GameCard> gameCards;


    public Player(PlayerId entityId, String userId) {
        super(entityId);
        this.score = new Score(0);
        this.userId = userId;
        this.gameCards = new HashSet<>();
    }

    public Score score() {
        return score;
    }

    public String userId() {
        return userId;
    }

    public Set<GameCard> gameCards() {
        return gameCards;
    }

    public void updateScore(Score score) {
        Integer value = Objects.requireNonNull(score.value());
        this.score = new Score(value);
    }

    public void addCard(GameCard gameCard) {
        this.gameCards.add(Objects.requireNonNull(gameCard));
    }

    public void removeCard(GameCard gameCard) {
        String cardId = Objects.requireNonNull(gameCard.value().card().id());
        this.gameCards.removeIf(
                gc -> Objects.equals(
                        gc.value().card().id(),
                        cardId
                )
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Player player = (Player) o;
        return userId.equals(player.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId);
    }
}
