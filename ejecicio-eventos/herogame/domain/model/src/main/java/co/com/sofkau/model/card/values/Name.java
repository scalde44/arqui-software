package co.com.sofkau.model.card.values;

import co.com.sofkau.model.generic.ValueObject;

import java.util.Objects;

public class Name implements ValueObject<String> {
    private static final String NOMBRE_REQUERIDO = "El nombre es requerido";
    private final String value;

    public Name(String value) {
        this.value = Objects.requireNonNull(value, NOMBRE_REQUERIDO);
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return value.equals(name.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
