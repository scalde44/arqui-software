package com.formacionbdi.app.usuarios.domain.models.entity;

import com.formacionbdi.app.usuarios.domain.models.dto.RoleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {
    private Long id;
    private String name;

    public RoleDto toRoleDto() {
        return new RoleDto(this);
    }
}
