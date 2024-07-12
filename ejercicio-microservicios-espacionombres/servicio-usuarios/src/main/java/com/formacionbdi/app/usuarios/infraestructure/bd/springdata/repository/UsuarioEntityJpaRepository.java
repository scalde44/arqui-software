package com.formacionbdi.app.usuarios.infraestructure.bd.springdata.repository;

import com.formacionbdi.app.usuarios.infraestructure.bd.springdata.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UsuarioEntityJpaRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByUsername(String username);
}
