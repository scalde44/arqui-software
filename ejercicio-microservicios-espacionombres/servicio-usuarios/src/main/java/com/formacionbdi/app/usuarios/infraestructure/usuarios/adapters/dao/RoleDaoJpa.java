package com.formacionbdi.app.usuarios.infraestructure.usuarios.adapters.dao;

import com.formacionbdi.app.usuarios.domain.exception.ExcepcionNoEncontradoRole;
import com.formacionbdi.app.usuarios.domain.models.dto.RoleDto;
import com.formacionbdi.app.usuarios.domain.ports.dao.RoleDao;
import com.formacionbdi.app.usuarios.infraestructure.bd.springdata.entity.RolEntity;
import com.formacionbdi.app.usuarios.infraestructure.bd.springdata.repository.RolEntityJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleDaoJpa implements RoleDao {
    private final RolEntityJpaRepository rolRepository;

    public RoleDaoJpa(RolEntityJpaRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public List<RoleDto> findAll() {
        return this.rolRepository.findAll().stream().map(RolEntity::toRoleDto).collect(Collectors.toList());
    }

    @Override
    public RoleDto findById(Long id) {
        return this.rolRepository.findById(id)
                .map(RolEntity::toRoleDto).orElseThrow(() -> new ExcepcionNoEncontradoRole("NO SE ENCONTRO"));
    }
}
