package co.com.sofkau.model.card;

import co.com.sofkau.model.card.values.Feature;
import co.com.sofkau.model.card.values.Image;
import co.com.sofkau.model.card.values.Name;
import co.com.sofkau.model.card.values.Power;

import java.util.Set;


public class Card {
    private String id;
    private Name name;
    private Power power;
    private Set<Feature> features;
    private Image image;

    public Card(String id, Name name, Power power, Set<Feature> features, Image image) {
        this.id = id;
        this.name = name;
        this.power = power;
        this.features = features;
        this.image = image;
    }

    public void updateName(Name name) {
        this.name = name;
    }

    public void updateImage(Image image) {
        this.image = image;
    }

    public void updatePower(Power power) {
        this.power = power;
    }

    public void updateFeatures(Set<Feature> features) {
        this.features = features;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name.value();
    }

    public Integer power() {
        return power.value();
    }

    public Set<Feature> features() {
        return Set.copyOf(features);
    }

    public String imageUrl() {
        return image.value();
    }
}
