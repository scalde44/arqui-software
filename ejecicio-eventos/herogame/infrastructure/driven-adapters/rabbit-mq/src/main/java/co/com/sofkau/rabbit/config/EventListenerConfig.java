package co.com.sofkau.rabbit.config;

import co.com.sofkau.model.game.events.*;
import co.com.sofkau.usecase.game.listeners.*;
import co.com.sofkau.usecase.generic.UseCaseWrap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class EventListenerConfig {
    @Bean
    public Set<UseCaseWrap> useCasesForListener(CreateRoundUseCase createRoundUseCase,
                                                DistributeCardsUseCase distributeCardsUseCase,
                                                AssignRoundPlayersUseCase assignRoundPlayersUseCase,
                                                DistributeWinningCardsUseCase distributeWinningCardsUseCase,
                                                RemoveLosingCardsUseCase removeLosingCardsUseCase,
                                                ValidateGameWinnerUseCase validateGameWinnerUseCase) {
        return Set.of(
                new UseCaseWrap(createRoundUseCase, GameStarted.EVENT_TYPE),
                new UseCaseWrap(distributeCardsUseCase, GameStarted.EVENT_TYPE),
                new UseCaseWrap(assignRoundPlayersUseCase, CreatedRound.EVENT_TYPE),
                new UseCaseWrap(distributeWinningCardsUseCase, FinishedRound.EVENT_TYPE),
                new UseCaseWrap(removeLosingCardsUseCase, DistributedWinningCards.EVENT_TYPE),
                new UseCaseWrap(validateGameWinnerUseCase, RemovedLosingCards.EVENT_TYPE)
        );
    }
}
