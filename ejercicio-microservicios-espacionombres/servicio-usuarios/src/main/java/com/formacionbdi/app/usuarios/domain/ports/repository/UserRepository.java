package com.formacionbdi.app.usuarios.domain.ports.repository;

import com.formacionbdi.app.usuarios.domain.models.entity.User;

public interface UserRepository {
    User save(User user);

    void deleteById(Long id);

    boolean existById(Long id);
}
