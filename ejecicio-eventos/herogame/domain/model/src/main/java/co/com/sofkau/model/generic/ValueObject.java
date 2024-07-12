package co.com.sofkau.model.generic;

import java.io.Serializable;

public interface ValueObject<T> extends Serializable {
    T value();
}
