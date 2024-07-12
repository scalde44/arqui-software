package co.com.sofkau.mongo.helper;

import co.com.sofkau.model.generic.DomainEvent;
import co.com.sofkau.model.generic.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class MongoEventRepository implements EventRepository {
    private final ReactiveMongoTemplate mongoTemplate;

    @Override
    public Mono<Void> save(DomainEvent domainEvent, String type) {
        return mongoTemplate.save(domainEvent, type).then();
    }
}
