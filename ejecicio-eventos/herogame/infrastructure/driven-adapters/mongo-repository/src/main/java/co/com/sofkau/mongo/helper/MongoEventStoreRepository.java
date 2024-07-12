package co.com.sofkau.mongo.helper;

import co.com.sofkau.model.generic.DomainEvent;
import co.com.sofkau.model.generic.EventStoreRepository;
import co.com.sofkau.model.generic.StoredEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;

@Component
@RequiredArgsConstructor
public class MongoEventStoreRepository implements EventStoreRepository {

    private final ReactiveMongoTemplate mongoTemplate;
    private final StoredEvent.EventSerializer eventSerializer;

    @Override
    public Flux<DomainEvent> getEventsBy(String aggregateName, String aggregateRootId) {
        var query = new Query(Criteria.where("aggregateRootId").is(aggregateRootId));
        return mongoTemplate.find(query, DocumentEventStored.class, aggregateName)
                .map((documentEventStored -> documentEventStored.getStoredEvent().deserializeEvent(eventSerializer)))
                .sort(Comparator.comparing(DomainEvent::getWhen));
    }

    @Override
    public Mono<Void> saveEvent(String aggregateName, String aggregateRootId, StoredEvent storedEvent) {
        var eventStored = new DocumentEventStored();
        eventStored.setAggregateRootId(aggregateRootId);
        eventStored.setStoredEvent(storedEvent);
        return mongoTemplate.save(eventStored, aggregateName).then();
    }
}