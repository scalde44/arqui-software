package com.formacionbdi.app.usuarios.infraestructure.bd.springdata.entity;

import com.formacionbdi.app.usuarios.domain.models.dto.RoleDto;
import com.formacionbdi.app.usuarios.domain.models.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 30)
    private String nombre;

    public Role toRole() {
        return new Role(getId(), getNombre());
    }

    public RoleDto toRoleDto() {
        return new RoleDto(getId(), getNombre());
    }

    public RolEntity(Role role) {
        this.id = role.getId();
        this.nombre = role.getName();
    }
}
