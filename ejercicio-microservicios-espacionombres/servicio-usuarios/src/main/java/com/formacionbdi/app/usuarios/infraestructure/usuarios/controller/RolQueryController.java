package com.formacionbdi.app.usuarios.infraestructure.usuarios.controller;

import com.formacionbdi.app.usuarios.domain.models.dto.RoleDto;
import com.formacionbdi.app.usuarios.domain.ports.dao.RoleDao;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rol")
public class RolQueryController {
    private final RoleDao roleDao;

    public RolQueryController(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RoleDto> findAll() {
        return this.roleDao.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoleDto findAll(@PathVariable Long id) {
        return this.roleDao.findById(id);
    }
}
