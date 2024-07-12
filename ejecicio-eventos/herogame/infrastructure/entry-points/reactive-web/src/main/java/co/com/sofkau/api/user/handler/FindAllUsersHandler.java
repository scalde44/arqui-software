package co.com.sofkau.api.user.handler;

import co.com.sofkau.api.user.dto.UserDTO;
import co.com.sofkau.api.user.helper.MapperUser;
import co.com.sofkau.usecase.user.FindAllUsersUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class FindAllUsersHandler {

    private final FindAllUsersUseCase useCase;

    private final MapperUser mapperUser;

    public Mono<ServerResponse> findAll(ServerRequest serverRequest){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(this.useCase.get()
                .map(this.mapperUser.mapToDTO()),UserDTO.class));
    }

}
