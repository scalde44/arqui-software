package co.com.sofkau.mongo.game.services;

import co.com.sofkau.model.game.Player;
import co.com.sofkau.model.game.events.AddedPlayer;
import co.com.sofkau.model.game.identities.GameId;
import co.com.sofkau.model.game.identities.PlayerId;
import co.com.sofkau.model.game.queries.GamePlayerService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
@RequiredArgsConstructor
public class GamePlayerMongoService implements GamePlayerService {
    private static final String AGGREGATE_ROOT_ID = "aggregateRootId";
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Mono<Player> getGamePlayerBy(GameId gameId, String userId) {
        return reactiveMongoTemplate
                .findOne(
                        new Query(where(AGGREGATE_ROOT_ID).is(gameId.value())),
                        AddedPlayerRecord.class,
                        AddedPlayer.EVENT_TYPE
                )
                .map(playerRecord ->
                        new Player(PlayerId.of(playerRecord.getIdentity().getUuid()), playerRecord.getUserId())
                );
    }

    @Data
    public static class AddedPlayerRecord {
        private String userId;
        private String aggregateRootId;
        private IdValue identity;
    }

    @Data
    public static class IdValue {
        private String uuid;
    }
}
