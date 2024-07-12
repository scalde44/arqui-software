package co.com.sofkau.mongo.card.collection;

import co.com.sofkau.mongo.card.collection.value.FeatureDocumentValue;
import co.com.sofkau.mongo.card.collection.value.ImageDocumentValue;
import co.com.sofkau.mongo.card.collection.value.NameDocumentValue;
import co.com.sofkau.mongo.card.collection.value.PowerDocumentValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Document(value = "card")
public class CardDocument {
    @Id
    private String id;
    private NameDocumentValue name;
    private PowerDocumentValue power;
    private Set<FeatureDocumentValue> features;
    private ImageDocumentValue image;
}
