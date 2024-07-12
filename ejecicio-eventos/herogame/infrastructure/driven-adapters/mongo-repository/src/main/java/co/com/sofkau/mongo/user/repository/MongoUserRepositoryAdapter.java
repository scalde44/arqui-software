package co.com.sofkau.mongo.user.repository;


import co.com.sofkau.model.user.User;
import co.com.sofkau.model.user.gateways.UserRepository;
import co.com.sofkau.mongo.helper.AdapterOperations;
import co.com.sofkau.mongo.user.collection.UserDocument;
import co.com.sofkau.mongo.user.collection.value.EmailDocumentValue;
import co.com.sofkau.mongo.user.helper.MapperUserEntity;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class MongoUserRepositoryAdapter extends AdapterOperations<User, UserDocument, String, MongoDBUserRepository>
        implements UserRepository {

    public MongoUserRepositoryAdapter(MongoDBUserRepository repository, MapperUserEntity mapper) {
        super(repository, mapper);
    }

    @Override
    public Mono<Boolean> findByEmail(String email) {
        var example = Example.of(UserDocument.builder().email(new EmailDocumentValue(email)).build());
        return repository.findBy(
                example,
                reactiveQuery -> reactiveQuery.exists()
        );
    }
}
