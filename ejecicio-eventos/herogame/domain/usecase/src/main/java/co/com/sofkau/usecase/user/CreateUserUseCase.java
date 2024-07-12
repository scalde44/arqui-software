package co.com.sofkau.usecase.user;


import co.com.sofkau.model.user.User;
import co.com.sofkau.model.user.gateways.UserRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RequiredArgsConstructor
public class CreateUserUseCase implements Function<User, Mono<User>> {

    private final UserRepository userRepository;

    @Override
    public Mono<User> apply(User user) {
        return this.userRepository.save(user);
    }
}
