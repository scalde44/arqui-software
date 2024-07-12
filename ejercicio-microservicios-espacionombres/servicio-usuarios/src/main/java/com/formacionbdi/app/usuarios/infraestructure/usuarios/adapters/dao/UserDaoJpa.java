package com.formacionbdi.app.usuarios.infraestructure.usuarios.adapters.dao;

import com.formacionbdi.app.usuarios.domain.exception.ExcepcionNoEncontradoUser;
import com.formacionbdi.app.usuarios.domain.models.dto.UserDto;
import com.formacionbdi.app.usuarios.domain.models.entity.User;
import com.formacionbdi.app.usuarios.domain.ports.dao.UserDao;
import com.formacionbdi.app.usuarios.infraestructure.bd.springdata.entity.UsuarioEntity;
import com.formacionbdi.app.usuarios.infraestructure.bd.springdata.repository.UsuarioEntityJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDaoJpa implements UserDao {
    private final UsuarioEntityJpaRepository userRepository;

    public UserDaoJpa(UsuarioEntityJpaRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDto> findAll() {
        return this.userRepository.findAll().stream().map(UsuarioEntity::toUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Long id) {
        return this.userRepository.findById(id)
                .map(UsuarioEntity::toUserDto)
                .orElseThrow(() -> new ExcepcionNoEncontradoUser("NO SE ENCONTRO"));
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username)
                .map(UsuarioEntity::toUser)
                .orElseThrow(() -> new ExcepcionNoEncontradoUser("NO SE ENCONTRO"));
    }
}
