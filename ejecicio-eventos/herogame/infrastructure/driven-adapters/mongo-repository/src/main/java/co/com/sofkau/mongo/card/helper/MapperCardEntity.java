package co.com.sofkau.mongo.card.helper;

import co.com.sofkau.model.card.Card;
import co.com.sofkau.model.card.values.Feature;
import co.com.sofkau.model.card.values.Image;
import co.com.sofkau.model.card.values.Name;
import co.com.sofkau.model.card.values.Power;
import co.com.sofkau.mongo.card.collection.CardDocument;
import co.com.sofkau.mongo.card.collection.value.FeatureDocumentValue;
import co.com.sofkau.mongo.card.collection.value.ImageDocumentValue;
import co.com.sofkau.mongo.card.collection.value.NameDocumentValue;
import co.com.sofkau.mongo.card.collection.value.PowerDocumentValue;
import co.com.sofkau.mongo.helper.MapperGeneric;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MapperCardEntity implements MapperGeneric<Card, CardDocument> {
    @Override
    public Card toEntity(CardDocument entity) {
        return new Card(
                entity.getId(),
                new Name(entity.getName().getValue()),
                new Power(entity.getPower().getValue()),
                featuresEntityToFeatures(entity.getFeatures()),
                new Image(
                        entity.getImage().getUrl()
                )
        );
    }

    @Override
    public CardDocument toData(Card model) {
        return CardDocument.builder()
                .id(model.id())
                .name(new NameDocumentValue(model.name()))
                .power(new PowerDocumentValue(model.power()))
                .features(featuresToFeaturesEntity(model.features()))
                .image(new ImageDocumentValue(model.imageUrl()))
                .build();
    }

    private Set<Feature> featuresEntityToFeatures(Set<FeatureDocumentValue> featuresEntity) {
        return featuresEntity.stream()
                .map(feature -> new Feature(feature.getValue()))
                .collect(Collectors.toSet());
    }

    private Set<FeatureDocumentValue> featuresToFeaturesEntity(Set<Feature> features) {
        return features.stream()
                .map(feature -> new FeatureDocumentValue(feature.value()))
                .collect(Collectors.toSet());
    }
}
