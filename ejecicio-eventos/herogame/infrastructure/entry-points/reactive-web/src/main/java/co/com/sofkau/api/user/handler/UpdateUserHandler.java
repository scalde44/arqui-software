package co.com.sofkau.api.user.handler;

import co.com.sofkau.api.user.dto.UserDTO;
import co.com.sofkau.api.user.helper.MapperUser;
import co.com.sofkau.usecase.user.UpdateUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UpdateUserHandler {

    private final UpdateUserUseCase useCase;
    private final MapperUser mapperUser;

    public Mono<ServerResponse> update(ServerRequest serverRequest){
        return serverRequest
                .bodyToMono(UserDTO.class)
                .zipWith(Mono.just(serverRequest.pathVariable("id")))
                .map(objects -> this.mapperUser.mapToModel(objects.getT2()).apply(objects.getT1()))
                .flatMap(user -> this.useCase.apply(user, user.id()))
                .map(this.mapperUser.mapToDTO())
                .flatMap(user -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(user)
                );
    }


}
