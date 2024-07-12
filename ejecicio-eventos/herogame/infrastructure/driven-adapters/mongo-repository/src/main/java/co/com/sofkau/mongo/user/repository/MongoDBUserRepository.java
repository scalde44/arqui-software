package co.com.sofkau.mongo.user.repository;


import co.com.sofkau.mongo.user.collection.UserDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface MongoDBUserRepository extends ReactiveMongoRepository<UserDocument, String>,
        ReactiveQueryByExampleExecutor<UserDocument> {
}

