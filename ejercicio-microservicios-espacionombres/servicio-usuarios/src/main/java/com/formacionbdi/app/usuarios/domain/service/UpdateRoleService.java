package com.formacionbdi.app.usuarios.domain.service;

import com.formacionbdi.app.usuarios.domain.models.dto.RoleDto;
import com.formacionbdi.app.usuarios.domain.ports.dao.RoleDao;
import com.formacionbdi.app.usuarios.domain.ports.repository.RoleRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateRoleService {
    private final RoleRepository roleRepository;
    private final RoleDao roleDao;

    public RoleDto ejecutar(RoleDto dto, Long id) {
        final RoleDto roleObtenido = obtenerPorId(id);
        final RoleDto roleActualizar = RoleDto.builder()
                .id(roleObtenido.getId())
                .name(dto.getName())
                .build();
        return this.roleRepository.save(roleActualizar).toRoleDto();
    }

    private RoleDto obtenerPorId(Long id) {
        return this.roleDao.findById(id);
    }
}
