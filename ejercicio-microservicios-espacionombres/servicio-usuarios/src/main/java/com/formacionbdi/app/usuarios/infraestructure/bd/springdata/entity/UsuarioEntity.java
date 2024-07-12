package com.formacionbdi.app.usuarios.infraestructure.bd.springdata.entity;

import com.formacionbdi.app.usuarios.domain.models.dto.UserDto;
import com.formacionbdi.app.usuarios.domain.models.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
public class UsuarioEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean enabled;
    @Column(length = 60)
    private String password;
    @Column(unique = true, length = 20)
    private String username;
    private String nombre;
    private String apellido;
    @Column(unique = true)
    private String email;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_id"})})
    private List<RolEntity> roles;
    private Integer intentos;

    public User toUser() {
        return User.builder()
                .id(getId())
                .email(getEmail())
                .enabled(getEnabled())
                .lastName(getApellido())
                .name(getNombre())
                .password(getPassword())
                .username(getUsername())
                .roles(getRoles().stream().map(RolEntity::toRole).collect(Collectors.toList()))
                .tries(getIntentos())
                .build();
    }

    public UserDto toUserDto() {
        return UserDto.builder()
                .id(getId())
                .email(getEmail())
                .enabled(getEnabled())
                .lastName(getApellido())
                .name(getNombre())
                .password(getPassword())
                .username(getUsername())
                .tries(getIntentos())
                .build();
    }

    public UsuarioEntity(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.enabled = user.getEnabled();
        this.apellido = user.getLastName();
        this.nombre = user.getName();
        this.password = user.getPassword();
        this.username = user.getUsername();
        this.roles = user.getRoles().stream().map(role -> new RolEntity(role)).collect(Collectors.toList());
        this.intentos = user.getTries();
    }
}
