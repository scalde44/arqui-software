package co.com.sofkau.api.user.controller;

import co.com.sofkau.api.helper.HandlerBase;
import co.com.sofkau.api.user.dto.UserDTO;
import co.com.sofkau.api.user.helper.MapperUser;
import co.com.sofkau.model.generic.EventBus;
import co.com.sofkau.model.generic.EventStoreRepository;
import co.com.sofkau.model.generic.StoredEvent;
import co.com.sofkau.usecase.user.CreateUserUseCase;
import co.com.sofkau.usecase.user.FindAllUsersUseCase;
import co.com.sofkau.usecase.user.UpdateUserUseCase;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@RestController
//@RequestMapping("/api/v1/users")
public class UserRestController extends HandlerBase {
    private final CreateUserUseCase createUserUseCase;
    private final FindAllUsersUseCase findAllUsersUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final MapperUser mapperUser;

    public UserRestController(EventStoreRepository repository, StoredEvent.EventSerializer eventSerializer, EventBus eventBus, CreateUserUseCase createUserUseCase, FindAllUsersUseCase findAllUsersUseCase, UpdateUserUseCase updateUserUseCase, MapperUser mapperUser) {
        super(repository, eventSerializer, eventBus);
        this.createUserUseCase = createUserUseCase;
        this.findAllUsersUseCase = findAllUsersUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.mapperUser = mapperUser;
    }

    @GetMapping
    public Flux<UserDTO> findAllUsers() {
        return this.findAllUsersUseCase.get()
                .map(this.mapperUser.mapToDTO());
    }

    @PostMapping
    public Mono<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        return Mono.just(userDTO)
                .map(dto -> this.mapperUser.mapToModel(null).apply(dto))
                .flatMap(this.createUserUseCase::apply)
                .map(this.mapperUser.mapToDTO());
    }

    @PutMapping("/{id}")
    public Mono<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable String id) {
        return Mono.just(userDTO)
                .map(dto -> this.mapperUser.mapToModel(id).apply(dto))
                .flatMap(user -> this.updateUserUseCase.apply(user, user.id()))
                .map(this.mapperUser.mapToDTO());
    }
}
