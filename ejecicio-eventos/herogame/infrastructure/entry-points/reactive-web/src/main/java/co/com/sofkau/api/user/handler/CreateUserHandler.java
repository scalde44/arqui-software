package co.com.sofkau.api.user.handler;


import co.com.sofkau.api.user.dto.UserDTO;
import co.com.sofkau.api.user.helper.MapperUser;
import co.com.sofkau.usecase.user.CreateUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CreateUserHandler {

    private final CreateUserUseCase useCase;
    private final MapperUser mapperUser;


    public Mono<ServerResponse> create(ServerRequest serverRequest) {
        return serverRequest
                .bodyToMono(UserDTO.class)
                .map(userDTO -> this.mapperUser.mapToModel(null).apply(userDTO))
                .flatMap(this.useCase::apply)
                .map(this.mapperUser.mapToDTO())
                .flatMap(card -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(card)
                );
    }

}
