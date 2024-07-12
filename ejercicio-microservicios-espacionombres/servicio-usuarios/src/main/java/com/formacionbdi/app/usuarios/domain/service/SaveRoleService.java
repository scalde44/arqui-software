package com.formacionbdi.app.usuarios.domain.service;

import com.formacionbdi.app.usuarios.domain.exception.ExcepcionDuplicidadRole;
import com.formacionbdi.app.usuarios.domain.models.dto.RoleDto;
import com.formacionbdi.app.usuarios.domain.ports.repository.RoleRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SaveRoleService {
    private final RoleRepository roleRepository;
    private static final String EL_ROLE_YA_EXISTE = "El role ya existe";

    public RoleDto ejecutar(RoleDto dto) {
        validarExistenciaPorId(dto.getId());
        return this.roleRepository.save(dto).toRoleDto();
    }

    private void validarExistenciaPorId(Long id) {
        boolean exist = this.roleRepository.existById(id);
        if (exist) {
            throw new ExcepcionDuplicidadRole(EL_ROLE_YA_EXISTE);
        }
    }
}
