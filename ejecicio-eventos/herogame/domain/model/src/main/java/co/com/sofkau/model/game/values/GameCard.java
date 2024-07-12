package co.com.sofkau.model.game.values;

import co.com.sofkau.model.card.Card;
import co.com.sofkau.model.generic.ValueObject;

import java.util.Objects;

public class GameCard implements ValueObject<GameCard.Properties> {
    private final Card card;
    private final Boolean isHidden;

    public GameCard(Card card, Boolean isHidden) {
        this.card = Objects.requireNonNull(card);
        this.isHidden = Objects.requireNonNull(isHidden);
    }

    @Override
    public Properties value() {
        return new Properties() {
            @Override
            public Card card() {
                return card;
            }

            @Override
            public Boolean isHidden() {
                return isHidden;
            }
        };
    }

    public interface Properties {
        Card card();

        Boolean isHidden();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameCard gameCard = (GameCard) o;
        return card.equals(gameCard.card) && isHidden.equals(gameCard.isHidden);
    }

    @Override
    public int hashCode() {
        return Objects.hash(card, isHidden);
    }
}
