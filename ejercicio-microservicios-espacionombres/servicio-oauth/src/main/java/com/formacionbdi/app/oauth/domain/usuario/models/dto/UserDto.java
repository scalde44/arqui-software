package com.formacionbdi.app.oauth.domain.usuario.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private Boolean enabled;
    private String password;
    private String username;
    private String name;
    private String lastName;
    private String email;
    private Integer tries;
}
