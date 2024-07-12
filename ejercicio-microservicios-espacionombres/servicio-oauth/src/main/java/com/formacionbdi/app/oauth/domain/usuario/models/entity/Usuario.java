package com.formacionbdi.app.oauth.domain.usuario.models.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Usuario implements Serializable {
    private Long id;
    private Boolean enabled;
    private String password;
    private String username;
    private String name;
    private String lastName;
    private String email;
    private List<Role> roles;
    private Integer tries;

    public List<Role> getRoles() {
        List<Role> rolesCopy = new ArrayList<>();
        if (roles != null) {
            rolesCopy = new ArrayList<>(roles);
        }
        return rolesCopy;
    }
}
