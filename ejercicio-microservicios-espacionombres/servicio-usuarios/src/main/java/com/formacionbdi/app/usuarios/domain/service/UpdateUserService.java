package com.formacionbdi.app.usuarios.domain.service;

import com.formacionbdi.app.usuarios.domain.models.dto.UserDto;
import com.formacionbdi.app.usuarios.domain.models.entity.User;
import com.formacionbdi.app.usuarios.domain.ports.dao.UserDao;
import com.formacionbdi.app.usuarios.domain.ports.repository.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateUserService {
    private final UserRepository userRepository;
    private final UserDao userDao;

    public User ejecutar(User user, Long id) {
        final UserDto userObtenido = obtenerPorId(id);
        User userActualizar = User.builder()
                .id(userObtenido.getId())
                .name(user.getName())
                .email(user.getEmail())
                .enabled(user.getEnabled())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .username(user.getUsername())
                .roles(user.getRoles())
                .tries(user.getTries()).build();
        return this.userRepository.save(userActualizar);
    }

    private UserDto obtenerPorId(Long id) {
        return this.userDao.findById(id);
    }
}
