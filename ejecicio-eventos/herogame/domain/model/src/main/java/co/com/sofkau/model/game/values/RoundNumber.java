package co.com.sofkau.model.game.values;

import co.com.sofkau.model.generic.ValueObject;

import java.util.Objects;

public class RoundNumber implements ValueObject<Integer> {
    private final Integer value;

    public RoundNumber(int value) {
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
        RoundNumber that = (RoundNumber) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
