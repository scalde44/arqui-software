package co.com.sofkau.model.game;

import co.com.sofkau.model.game.events.*;
import co.com.sofkau.model.game.factories.CardsByPlayerFactory;
import co.com.sofkau.model.game.factories.PlayerFactory;
import co.com.sofkau.model.game.identities.BoardId;
import co.com.sofkau.model.game.identities.GameId;
import co.com.sofkau.model.game.identities.PlayerId;
import co.com.sofkau.model.game.identities.RoundId;
import co.com.sofkau.model.game.values.DateGame;
import co.com.sofkau.model.game.values.GameCard;
import co.com.sofkau.model.game.values.RoundNumber;
import co.com.sofkau.model.generic.AggregateEvent;
import co.com.sofkau.model.generic.DomainEvent;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Game extends AggregateEvent<GameId> {
    protected Board board;
    protected Set<Player> players;
    protected Set<Round> rounds;
    protected DateGame dateGame;
    protected Boolean isPlaying;
    protected Boolean isFinished;
    protected Player winner;

    public Game(GameId gameId, PlayerFactory playerFactory, DateGame dateGame) {
        super(gameId);
        subscribe(new GameEventChange(this));
        appendChange(new CreatedGame(dateGame)).apply();
        playerFactory.players()
                .forEach(player ->
                        appendChange(new AddedPlayer(player.identity(), player.userId())).apply()
                );
        appendChange(new CreatedBoard(new BoardId())).apply();
    }

    private Game(GameId gameId) {
        super(gameId);
        subscribe(new GameEventChange(this));
    }

    public static Game from(GameId gameId, List<DomainEvent> events) {
        var game = new Game(gameId);
        events.forEach(game::applyEvent);
        return game;
    }

    public void startGame() {
        appendChange(new GameStarted(this.entityId)).apply();
    }

    public void finishGame(Player winner) {
        appendChange(new GameFinished(this.entityId, winner)).apply();
    }

    public void distributeCards(CardsByPlayerFactory cardsByPlayerFactory) {
        cardsByPlayerFactory.cardsByPlayer()
                .forEach((playerId, gameCards) ->
                        appendChange(new DistributedCards(playerId, gameCards)).apply()
                );
    }

    public void distributeWinningCards(PlayerId playerId, RoundId roundId) {
        appendChange(new DistributedWinningCards(this.entityId, playerId, roundId)).apply();
    }

    public void removeLosingCards(PlayerId playerId, RoundId roundId) {
        players.stream()
                .filter(player -> !player.identity().equals(playerId))
                .forEach(
                        player -> appendChange(new RemovedLosingCards(this.entityId, playerId, player.identity(), roundId)).apply()
                );
    }

    public void createRound(RoundId roundId) {
        appendChange(new CreatedRound(this.entityId, roundId, new RoundNumber(rounds.size() + 1))).apply();
    }

    public void assignRoundPlayers(RoundId roundId) {
        appendChange(new AssignedRoundPlayers(roundId, getPlayersToRound())).apply();
    }

    public void addCardToBoard(PlayerId playerId, GameCard gameCard) {
        appendChange(new AddedBoardCard(playerId, gameCard, roundActive())).apply();
    }

    public void emptyBoard() {
        appendChange(new CleanedBoard()).apply();
    }

    public void finishRound(RoundId roundId) {
        appendChange(new FinishedRound(this.entityId, roundId, getWinnerBy(roundId))).apply();
    }

    private Set<PlayerId> getPlayersToRound() {
        return players.stream()
                .filter(player -> player.gameCards().size() > 0)
                .map(Player::identity)
                .collect(Collectors.toSet());
    }

    private PlayerId getWinnerBy(RoundId roundId) {
        Round roundFound = rounds.stream()
                .filter(round -> Objects.equals(round.identity(), roundId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No se encontro la ronda"));
        var cardMax = roundFound.playerIds().stream()
                .filter(playerId -> board.existCardByPlayerId(playerId))
                .map(playerId -> board.cardByPlayerId(playerId))
                .max(Comparator.comparing(gameCard -> gameCard.value().card().power()))
                .orElseThrow(() -> new IllegalArgumentException("No se encontro ganador"));
        return board.cardByPlayer().entrySet()
                .stream()
                .filter(entry -> cardMax.value().card().id().equals(entry.getValue().value().card().id()))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("No se encontro ganador")).getKey();

    }

    protected Set<GameCard> losingCards(PlayerId winnerId) {
        return board.cardByPlayer()
                .entrySet()
                .stream()
                .filter(mapita -> !mapita.getKey().equals(winnerId))
                .map(mapita -> mapita.getValue())
                .collect(Collectors.toSet());
    }

    public Round roundActive() {
        return this.rounds.stream()
                .min(Comparator.comparing(Round::roundNumber))
                .orElseThrow(() -> new IllegalArgumentException("Ronda no encontrada"));
    }

    public Set<Player> players() {
        return Set.copyOf(players);
    }

    public Boolean isPlaying() {
        return isPlaying;
    }
}
