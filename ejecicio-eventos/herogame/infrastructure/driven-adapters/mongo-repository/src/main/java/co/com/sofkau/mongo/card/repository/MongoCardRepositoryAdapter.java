package co.com.sofkau.mongo.card.repository;

import co.com.sofkau.model.card.Card;
import co.com.sofkau.model.card.gateways.CardRepository;
import co.com.sofkau.mongo.card.collection.CardDocument;
import co.com.sofkau.mongo.card.helper.MapperCardEntity;
import co.com.sofkau.mongo.helper.AdapterOperations;
import org.springframework.stereotype.Repository;

@Repository
public class MongoCardRepositoryAdapter
        extends AdapterOperations<Card, CardDocument, String, MongoDBCardRepository>
        implements CardRepository {

    public MongoCardRepositoryAdapter(MongoDBCardRepository repository, MapperCardEntity mapper) {
        super(repository, mapper);
    }
}
