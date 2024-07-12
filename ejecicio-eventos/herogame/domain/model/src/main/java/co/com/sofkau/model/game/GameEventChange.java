package co.com.sofkau.model.game;

import co.com.sofkau.model.game.events.*;
import co.com.sofkau.model.game.values.GameCard;
import co.com.sofkau.model.generic.EventChange;

import java.util.HashSet;
import java.util.Objects;

public class GameEventChange extends EventChange {
    public GameEventChange(Game game) {
        apply((CreatedGame event) -> {
            game.dateGame = event.getDateGame();
            game.players = new HashSet<>();
            game.rounds = new HashSet<>();
            game.isPlaying = Boolean.FALSE;
            game.isFinished = Boolean.FALSE;
        });

        apply((AddedPlayer event) -> {
            game.players.add(new Player(event.getIdentity(), event.getUserId()));
        });

        apply((CreatedBoard event) -> {
            game.board = new Board(event.getBoardId());
        });
        apply((GameStarted event) -> {
            game.isPlaying = Boolean.TRUE;
        });
        apply((DistributedCards event) -> {
            game.players.stream()
                    .filter(player ->
                            Objects.equals(player.identity(), event.getPlayerId()))
                    .findFirst()
                    .ifPresent(player ->
                            event.getGameCards().forEach(player::addCard)
                    );
        });

        apply((CreatedRound event) -> {
            game.rounds.add(new Round(event.getRoundId(), event.getRoundNumber()));
        });
        apply((FinishedRound event) -> {
            game.rounds.stream()
                    .filter(round -> Objects.equals(round.identity(), event.getRoundId()))
                    .findFirst()
                    .ifPresent(round -> round.finish(event.getWinner()));
        });

        apply((AssignedRoundPlayers event) -> {
            game.rounds.stream()
                    .filter(round -> Objects.equals(round.identity(), event.getRoundId()))
                    .findFirst()
                    .ifPresent(round ->
                            event.getPlayers().forEach(round::addPlayer));
        });

        apply((DistributedWinningCards event) -> {
            game.players.stream()
                    .filter(player -> player.identity().equals(event.getWinnerId()))
                    .findFirst()
                    .ifPresent(
                            player -> {
                                game.losingCards(event.getWinnerId()).forEach(gameCard -> player.addCard(gameCard));
                            }
                    );
        });

        apply((RemovedLosingCards event) -> {
            game.players.stream()
                    .filter(player -> player.identity().equals(event.getLoserId()))
                    .findFirst()
                    .ifPresent(
                            player -> {
                                GameCard gameCard = game.board.cardByPlayer().get(player.identity());
                                player.removeCard(gameCard);
                            }
                    );
        });

        apply((AddedBoardCard event) -> {
            game.board.addCard(event.getPlayerId(), event.getGameCard());
        });

        apply((CleanedBoard event) -> {
            game.board.clearAll();
        });
        apply((GameFinished event) -> {
            game.isFinished = Boolean.TRUE;
            game.winner = event.getWinner();
        });
    }
}
