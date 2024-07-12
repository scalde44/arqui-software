package co.com.sofkau.api.game.helper;

import co.com.sofkau.api.game.dto.GamePlayerDTO;
import co.com.sofkau.model.game.Player;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MapperGamePlayer {

    public Function<Player, GamePlayerDTO> mapToDTO() {
        return entity -> new GamePlayerDTO(entity.userId(), entity.identity().value());
    }
}
