package com.formacionbdi.app.usuarios.infraestructure.usuarios.adapters.repository;

import com.formacionbdi.app.usuarios.domain.models.dto.RoleDto;
import com.formacionbdi.app.usuarios.domain.models.entity.Role;
import com.formacionbdi.app.usuarios.domain.ports.repository.RoleRepository;
import com.formacionbdi.app.usuarios.infraestructure.bd.springdata.entity.RolEntity;
import com.formacionbdi.app.usuarios.infraestructure.bd.springdata.repository.RolEntityJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepositoryJpa implements RoleRepository {
    private final RolEntityJpaRepository rolRepository;

    public RoleRepositoryJpa(RolEntityJpaRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public Role save(RoleDto dto) {
        RolEntity rol = new RolEntity(dto.getId(), dto.getName());
        return this.rolRepository.save(rol).toRole();
    }

    @Override
    public void deleteById(Long id) {
        this.rolRepository.deleteById(id);
    }

    @Override
    public boolean existById(Long id) {
        if (id == null) {
            return false;
        }
        return this.rolRepository.existsById(id);
    }
}
