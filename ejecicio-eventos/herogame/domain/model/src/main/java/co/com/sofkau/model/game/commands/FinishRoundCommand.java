package co.com.sofkau.model.game.commands;

import co.com.sofkau.model.generic.Command;

public class FinishRoundCommand extends Command {
    private String gameId;
    private String roundId;

    public FinishRoundCommand() {
    }

    public FinishRoundCommand(String gameId, String roundId) {
        this.gameId = gameId;
        this.roundId = roundId;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getRoundId() {
        return roundId;
    }

    public void setRoundId(String roundId) {
        this.roundId = roundId;
    }
}
