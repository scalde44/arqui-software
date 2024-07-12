package co.com.sofkau.model.card.values;

import co.com.sofkau.model.generic.ValueObject;

import java.util.Objects;

public class Feature implements ValueObject<String> {
    private static final String FEATURE_REQUERIDO = "La feature es requerida";
    private final String value;

    public Feature(String value) {
        this.value = Objects.requireNonNull(value, FEATURE_REQUERIDO);
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feature feature = (Feature) o;
        return value.equals(feature.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
