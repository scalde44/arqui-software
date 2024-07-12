package co.com.sofkau.usecase.user;

import co.com.sofkau.model.user.User;
import co.com.sofkau.model.user.gateways.UserRepository;
import co.com.sofkau.model.user.values.Email;
import co.com.sofkau.model.user.values.Image;
import co.com.sofkau.model.user.values.Name;
import co.com.sofkau.model.user.values.Role;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@RequiredArgsConstructor
public class UpdateUserUseCase implements BiFunction<User, String, Mono<User>> {
    private final UserRepository userRepository;
    @Override
        public Mono<User> apply(User user, String id) {
        return this.userRepository.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("User no encontrado")))
                .map(u ->{
                    u.updateName(new Name(user.name()));
                    u.updateEmail(new Email(user.email()));
                    u.updateRole(new Role(user.role()));
                    u.updateImage(new Image(user.image()));
                    return u;
                })
                .flatMap(this.userRepository::save);
    }
}
