package com.formacionbdi.app.usuarios.infraestructure.usuarios.controller;

import com.formacionbdi.app.usuarios.domain.models.dto.UserDto;
import com.formacionbdi.app.usuarios.domain.models.entity.User;
import com.formacionbdi.app.usuarios.domain.ports.dao.UserDao;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuario")
public class UsuarioQueryController {
    private final UserDao userDao;

    public UsuarioQueryController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> findAll() {
        return this.userDao.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto findById(@PathVariable Long id) {
        return this.userDao.findById(id);
    }

    @GetMapping("/username/{username}")
    @ResponseStatus(HttpStatus.OK)
    public User findByUsername(@PathVariable String username) {
        return this.userDao.findByUsername(username);
    }
}
