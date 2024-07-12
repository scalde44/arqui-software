package co.com.sofkau.model.user.values;

import co.com.sofkau.model.generic.ValueObject;

import java.util.Objects;

public class Role implements ValueObject<Role.Roles> {



    public enum Roles {
        ADMIN, PLAYER
    }
    private static final String ROL_REQUERIDO = "El rol es requerido";
    private final Roles rol;

    public Role(Roles rol) {
        this.rol = rol;
    }

    @Override
    public Roles value() {
        return rol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return rol == role.rol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rol);
    }
}
