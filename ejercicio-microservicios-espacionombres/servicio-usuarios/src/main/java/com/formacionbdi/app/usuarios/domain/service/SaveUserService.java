package com.formacionbdi.app.usuarios.domain.service;

import com.formacionbdi.app.usuarios.domain.exception.ExceptionDuplicidadUser;
import com.formacionbdi.app.usuarios.domain.models.entity.User;
import com.formacionbdi.app.usuarios.domain.ports.repository.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SaveUserService {
    private final UserRepository userRepository;
    private static final String EL_USER_YA_EXISTE = "El user ya existe";

    public User ejecutar(User user) {
        validarExistenciaPorId(user.getId());
        return this.userRepository.save(user);
    }

    private void validarExistenciaPorId(Long id) {
        boolean exist = this.userRepository.existById(id);
        if (exist) {
            throw new ExceptionDuplicidadUser(EL_USER_YA_EXISTE);
        }
    }
}
