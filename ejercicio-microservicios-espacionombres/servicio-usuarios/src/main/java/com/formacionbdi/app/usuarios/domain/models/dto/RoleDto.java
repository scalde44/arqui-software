package com.formacionbdi.app.usuarios.domain.models.dto;

import com.formacionbdi.app.usuarios.domain.models.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDto {
    private Long id;
    private String name;

    public RoleDto(Role role) {
        this.id = role.getId();
        this.name = role.getName();
    }
}
