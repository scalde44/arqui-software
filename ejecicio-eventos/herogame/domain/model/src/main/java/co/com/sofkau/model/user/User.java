package co.com.sofkau.model.user;

import co.com.sofkau.model.user.values.Email;
import co.com.sofkau.model.user.values.Image;
import co.com.sofkau.model.user.values.Name;
import co.com.sofkau.model.user.values.Role;

public class User {

    private String id;
    private Name name;
    private Email email;
    private Role role;
    private Image image;

    public User(String id, Name name, Email email, Role role, Image image) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.image = image;
    }

    public String id() {
        return id;
    }



    public String name() {
        return name.value();
    }

    public void updateName(Name name) {
        this.name = name;
    }

    public String email() {
        return email.value();
    }

    public void updateEmail(Email email) {
        this.email = email;
    }

    public Role.Roles role() {
        return role.value();
    }

    public void updateRole(Role role) {
        this.role = role;
    }

    public String image() {
        return image.value();
    }

    public void updateImage(Image image) {
        this.image = image;
    }
}
