package com.formacionbdi.app.usuarios.domain.service;

import com.formacionbdi.app.usuarios.domain.ports.repository.RoleRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteRoleService {
    private final RoleRepository roleRepository;

    public void ejecutar(Long id) {
        this.roleRepository.deleteById(id);
    }
}
