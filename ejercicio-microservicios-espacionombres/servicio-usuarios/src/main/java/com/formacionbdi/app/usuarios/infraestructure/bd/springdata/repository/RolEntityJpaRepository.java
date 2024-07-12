package com.formacionbdi.app.usuarios.infraestructure.bd.springdata.repository;

import com.formacionbdi.app.usuarios.infraestructure.bd.springdata.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolEntityJpaRepository extends JpaRepository<RolEntity, Long> {
}
