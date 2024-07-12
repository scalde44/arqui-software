package com.formacionbdi.app.usuarios.domain.ports.dao;

import com.formacionbdi.app.usuarios.domain.models.dto.UserDto;
import com.formacionbdi.app.usuarios.domain.models.entity.User;

import java.util.List;

public interface UserDao {
    List<UserDto> findAll();

    UserDto findById(Long id);

    User findByUsername(String username);
}
