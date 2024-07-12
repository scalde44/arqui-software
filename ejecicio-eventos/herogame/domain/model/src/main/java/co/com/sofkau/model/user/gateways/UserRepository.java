package co.com.sofkau.model.user.gateways;


import co.com.sofkau.model.user.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository {

    Mono<User> save(User user);

    Mono<User> findById(String id);

    Flux<User> findAll();

    Mono<Boolean> findByEmail(String email);
}
