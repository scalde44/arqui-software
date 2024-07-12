package co.com.sofkau.model.user.values;

import co.com.sofkau.model.generic.ValueObject;

import java.util.Objects;

public class Email implements ValueObject<String> {

    private static final String EMAIL_REQUERIDO = "El email es requerido";
    private final String value;

    public Email(String value) {
        this.value = Objects.requireNonNull(value, EMAIL_REQUERIDO);
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return value.equals(email.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
