package edu.co.usbcali.arqui.ejercicio.repository;

import edu.co.usbcali.arqui.ejercicio.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class H2DBUserRepositoryAdapter {
    private final H2DBUserRepository userRepository;

    public User getById(String id) {
        return this.userRepository.findById(id)
                .map(user -> new User(user.getId(), user.getName(), user.getEmail()))
                .orElseThrow();
    }

    public void save(User user) {
        this.userRepository.save(new User(user.getName(), user.getEmail()));
    }
}
