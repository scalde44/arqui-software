package co.com.sofkau.model.card.values;

import co.com.sofkau.model.generic.ValueObject;

import java.util.Objects;

public class Image implements ValueObject<String> {
    private static final String URL_REQUERIDA = "La url es requerida";
    private final String url;

    public Image(String url) {
        this.url = Objects.requireNonNull(url, URL_REQUERIDA);
    }

    @Override
    public String value() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return url.equals(image.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}
