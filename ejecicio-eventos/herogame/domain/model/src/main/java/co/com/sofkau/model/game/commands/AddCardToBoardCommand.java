package co.com.sofkau.model.game.commands;

import co.com.sofkau.model.generic.Command;

public class AddCardToBoardCommand extends Command {
    private String gameId;
    private String playerId;
    private String cardId;

    public AddCardToBoardCommand() {
    }

    public AddCardToBoardCommand(String gameId, String playerId, String cardId) {
        this.gameId = gameId;
        this.playerId = playerId;
        this.cardId = cardId;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}
