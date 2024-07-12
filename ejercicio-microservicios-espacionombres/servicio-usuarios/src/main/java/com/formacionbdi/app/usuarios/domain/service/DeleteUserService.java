package com.formacionbdi.app.usuarios.domain.service;

import com.formacionbdi.app.usuarios.domain.ports.repository.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteUserService {
    private final UserRepository userRepository;

    public void ejecutar(Long id) {
        this.userRepository.deleteById(id);
    }
}
