package com.formacionbdi.app.usuarios.infraestructure.usuarios.adapters.repository;

import com.formacionbdi.app.usuarios.domain.models.entity.User;
import com.formacionbdi.app.usuarios.domain.ports.repository.UserRepository;
import com.formacionbdi.app.usuarios.infraestructure.bd.springdata.entity.UsuarioEntity;
import com.formacionbdi.app.usuarios.infraestructure.bd.springdata.repository.UsuarioEntityJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryJpa implements UserRepository {
    private final UsuarioEntityJpaRepository userRepository;

    public UserRepositoryJpa(UsuarioEntityJpaRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        UsuarioEntity usuario = new UsuarioEntity(user);
        return this.userRepository.save(usuario).toUser();
    }

    @Override
    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public boolean existById(Long id) {
        if (id == null) {
            return false;
        }
        return this.userRepository.existsById(id);
    }
}
