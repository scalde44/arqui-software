package co.com.sofkau.model.game.queries;

import co.com.sofkau.model.game.Player;
import co.com.sofkau.model.game.identities.GameId;
import reactor.core.publisher.Mono;

public interface GamePlayerService {
    Mono<Player> getGamePlayerBy(GameId gameId, String userId);
}