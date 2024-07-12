package co.com.sofkau.mongo.card.repository;

import co.com.sofkau.mongo.card.collection.CardDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface MongoDBCardRepository
        extends ReactiveMongoRepository<CardDocument, String>,
        ReactiveQueryByExampleExecutor<CardDocument> {
}
