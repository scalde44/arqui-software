package co.com.sofkau.api.card.helper;

import co.com.sofkau.api.card.dto.CardDTO;
import co.com.sofkau.api.helper.MapperGenericDto;
import co.com.sofkau.model.card.Card;
import co.com.sofkau.model.card.values.Feature;
import co.com.sofkau.model.card.values.Image;
import co.com.sofkau.model.card.values.Name;
import co.com.sofkau.model.card.values.Power;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class MapperCard implements MapperGenericDto<CardDTO, Card> {

    @Override
    public Function<CardDTO, Card> mapToModel(String id) {
        return cardDTO -> new Card(
                id,
                new Name(cardDTO.getName()),
                new Power(cardDTO.getPower().intValue()),
                featuresDtoToFeatures(cardDTO.getFeatures()),
                new Image(cardDTO.getImageUrl())
        );
    }

    @Override
    public Function<Card, CardDTO> mapToDTO() {
        return entity -> CardDTO.builder()
                .id(entity.id())
                .name(entity.name())
                .power(entity.power())
                .features(featuresToFeaturesDto(entity.features()))
                .imageUrl(entity.imageUrl())
                .build();
    }

    private Set<Feature> featuresDtoToFeatures(Set<String> featuresDto) {
        return featuresDto.stream().map(feature -> new Feature(feature)).collect(Collectors.toSet());
    }

    private Set<String> featuresToFeaturesDto(Set<Feature> features) {
        return features.stream().map(feature -> feature.value()).collect(Collectors.toSet());
    }


}
