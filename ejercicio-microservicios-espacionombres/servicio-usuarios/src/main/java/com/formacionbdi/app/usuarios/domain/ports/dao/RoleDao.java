package com.formacionbdi.app.usuarios.domain.ports.dao;

import com.formacionbdi.app.usuarios.domain.models.dto.RoleDto;

import java.util.List;

public interface RoleDao {
    List<RoleDto> findAll();

    RoleDto findById(Long id);
}
