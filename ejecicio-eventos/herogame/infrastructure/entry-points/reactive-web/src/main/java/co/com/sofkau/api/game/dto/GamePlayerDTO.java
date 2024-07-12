package co.com.sofkau.api.game.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GamePlayerDTO {
    private String userId;
    private String playerId;
}
