package co.com.sofkau.model.game.values;

import co.com.sofkau.model.generic.ValueObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DateGame implements ValueObject<String> {

    private final LocalDate date;
    private final String format;

    public DateGame(LocalDate localDate) {
        this.date = Objects.requireNonNull(localDate);
        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha no puede ser mayor a la actual");
        }
        format = generateFormat();
    }

    private String generateFormat() {
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    @Override
    public String value() {
        return format;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DateGame dateGame = (DateGame) o;
        return format.equals(dateGame.format);
    }

    @Override
    public int hashCode() {
        return Objects.hash(format);
    }
}
