package co.com.sofkau.model.card.values;

import co.com.sofkau.model.generic.ValueObject;

import java.util.Objects;

public class Power implements ValueObject<Integer> {
    private static final String POWER_REQUERIDO = "El power es requerido";
    private static final String POWER_DEBE_SER_MAYOR = "El power debe ser mayor a %s";
    private static final String POWER_DEBE_SER_MENOR_IGUAL = "El power debe ser menor o igual a %s";
    private static final int POWER_MINIMO = 0;
    private static final int POWER_MAXIMO = 100;
    private final Integer value;

    public Power(int value) {
        this.value = value;
        if (value < POWER_MINIMO) {
            throw new IllegalArgumentException(String.format(POWER_DEBE_SER_MAYOR, POWER_MINIMO));
        }
        if (value > POWER_MAXIMO) {
            throw new IllegalArgumentException(String.format(POWER_DEBE_SER_MENOR_IGUAL, POWER_MAXIMO));
        }
    }

    @Override
    public Integer value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Power power = (Power) o;
        return value.equals(power.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
