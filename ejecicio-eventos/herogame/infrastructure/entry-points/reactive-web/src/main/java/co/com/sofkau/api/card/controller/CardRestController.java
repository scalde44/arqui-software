package co.com.sofkau.api.card.controller;

import co.com.sofkau.api.card.dto.CardDTO;
import co.com.sofkau.api.card.helper.MapperCard;
import co.com.sofkau.api.helper.HandlerBase;
import co.com.sofkau.model.generic.EventBus;
import co.com.sofkau.model.generic.EventStoreRepository;
import co.com.sofkau.model.generic.StoredEvent;
import co.com.sofkau.usecase.card.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@RestController
//@RequestMapping("/api/v1/cards")
public class CardRestController extends HandlerBase {
    private final CreateCardUseCase createCardUseCase;
    private final UpdateCardUseCase updateCardUseCase;
    private final GetCardByIdUseCase getCardByIdUseCase;
    private final FindAllCardsUseCase findAllCardsUseCase;
    private final DeleteCardUseCase deleteCardUseCase;
    private final MapperCard mapperCard;

    public CardRestController(EventStoreRepository repository, StoredEvent.EventSerializer eventSerializer, EventBus eventBus, CreateCardUseCase createCardUseCase, UpdateCardUseCase updateCardUseCase, GetCardByIdUseCase getCardByIdUseCase, FindAllCardsUseCase findAllCardsUseCase, DeleteCardUseCase deleteCardUseCase, MapperCard mapperCard) {
        super(repository, eventSerializer, eventBus);
        this.createCardUseCase = createCardUseCase;
        this.updateCardUseCase = updateCardUseCase;
        this.getCardByIdUseCase = getCardByIdUseCase;
        this.findAllCardsUseCase = findAllCardsUseCase;
        this.deleteCardUseCase = deleteCardUseCase;
        this.mapperCard = mapperCard;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CardDTO> createCard(@RequestBody CardDTO cardDTO) {
        return Mono.just(cardDTO)
                .map(dto -> this.mapperCard.mapToModel(null).apply(dto))
                .flatMap(this.createCardUseCase::apply)
                .map(this.mapperCard.mapToDTO());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CardDTO> createCard(@RequestBody CardDTO cardDTO, @PathVariable String id) {
        return Mono.just(cardDTO)
                .map(dto -> this.mapperCard.mapToModel(id).apply(dto))
                .flatMap(card -> this.updateCardUseCase.apply(card, card.id()))
                .map(this.mapperCard.mapToDTO());
    }

    @GetMapping
    public Flux<CardDTO> getAllCards() {
        return this.findAllCardsUseCase.get()
                .map(this.mapperCard.mapToDTO());
    }

    @GetMapping("/{id}")
    public Mono<CardDTO> getCardById(@PathVariable String id) {
        return this.getCardByIdUseCase.cardById(id)
                .map(this.mapperCard.mapToDTO());
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteCard(@PathVariable String id) {
        return this.deleteCardUseCase.apply(id);
    }
}
