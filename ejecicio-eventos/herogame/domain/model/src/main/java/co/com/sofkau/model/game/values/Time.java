package co.com.sofkau.model.game.values;

import co.com.sofkau.model.generic.ValueObject;

import java.time.Duration;
import java.util.Objects;

public class Time implements ValueObject<Duration> {
    public static final int DURATION_DEFAULT = 60;
    public static final int DURATION_ZERO = 0;
    private final Duration duration;

    public Time(int seconds) {
        if (seconds < 0) {
            throw new IllegalArgumentException("Ingrese un tiempo valido");
        }
        this.duration = Duration.ofSeconds(seconds);
    }

    @Override
    public Duration value() {
        return duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return duration.equals(time.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(duration);
    }

    public Time clear() {
        return new Time(DURATION_ZERO);
    }
}
