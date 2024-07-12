package com.formacionbdi.app.usuarios.domain.ports.repository;

import com.formacionbdi.app.usuarios.domain.models.dto.RoleDto;
import com.formacionbdi.app.usuarios.domain.models.entity.Role;

public interface RoleRepository {
    Role save(RoleDto dto);

    void deleteById(Long id);

    boolean existById(Long id);
}
