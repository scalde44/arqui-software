package co.com.sofkau.model.game.values;

import co.com.sofkau.model.generic.ValueObject;

import java.util.Objects;

public class Score implements ValueObject<Integer> {

    private final Integer value;

    public Score(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("el valor no debe ser negativo");
        }
        this.value = value;
    }

    @Override
    public Integer value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return value.equals(score.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
