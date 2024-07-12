package com.formacionbdi.app.usuarios.infraestructure.usuarios.controller;

import com.formacionbdi.app.usuarios.domain.models.dto.RoleDto;
import com.formacionbdi.app.usuarios.domain.service.DeleteRoleService;
import com.formacionbdi.app.usuarios.domain.service.SaveRoleService;
import com.formacionbdi.app.usuarios.domain.service.UpdateRoleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rol")
public class RolCommandController {
    private final SaveRoleService saveRoleService;
    private final UpdateRoleService updateRoleService;
    private final DeleteRoleService deleteRoleService;

    public RolCommandController(SaveRoleService saveRoleService, UpdateRoleService updateRoleService, DeleteRoleService deleteRoleService) {
        this.saveRoleService = saveRoleService;
        this.updateRoleService = updateRoleService;
        this.deleteRoleService = deleteRoleService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RoleDto save(@RequestBody RoleDto roleDto) {
        return this.saveRoleService.ejecutar(roleDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public RoleDto update(@RequestBody RoleDto roleDto, @PathVariable Long id) {
        return this.updateRoleService.ejecutar(roleDto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        this.deleteRoleService.ejecutar(id);
    }
}
