package com.formacionbdi.app.usuarios.infraestructure.usuarios.controller;

import com.formacionbdi.app.usuarios.domain.models.entity.User;
import com.formacionbdi.app.usuarios.domain.service.DeleteUserService;
import com.formacionbdi.app.usuarios.domain.service.SaveUserService;
import com.formacionbdi.app.usuarios.domain.service.UpdateUserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuario")
public class UsuarioCommandController {
    private final SaveUserService saveUserService;
    private final UpdateUserService updateUserService;
    private final DeleteUserService deleteUserService;

    public UsuarioCommandController(SaveUserService saveUserService, UpdateUserService updateUserService, DeleteUserService deleteUserService) {
        this.saveUserService = saveUserService;
        this.updateUserService = updateUserService;
        this.deleteUserService = deleteUserService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody User user) {
        return this.saveUserService.ejecutar(user);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public User update(@RequestBody User user, @PathVariable Long id) {
        return this.updateUserService.ejecutar(user, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void deleteById(@PathVariable Long id) {
        this.deleteUserService.ejecutar(id);
    }
}
