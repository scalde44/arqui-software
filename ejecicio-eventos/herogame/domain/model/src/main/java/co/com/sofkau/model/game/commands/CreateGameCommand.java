package co.com.sofkau.model.game.commands;

import co.com.sofkau.model.generic.Command;

import java.util.Set;

public class CreateGameCommand extends Command {

    private String gameId;
    private Set<String> usersId;

    public CreateGameCommand() {
    }

    public CreateGameCommand(String gameId, Set<String> usersId) {
        this.gameId = gameId;
        this.usersId = usersId;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public Set<String> getUsersId() {
        return usersId;
    }

    public void setUsersId(Set<String> usersId) {
        this.usersId = usersId;
    }
}
