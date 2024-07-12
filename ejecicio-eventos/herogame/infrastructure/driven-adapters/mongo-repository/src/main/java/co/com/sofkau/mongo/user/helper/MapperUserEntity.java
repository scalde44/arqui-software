package co.com.sofkau.mongo.user.helper;

import co.com.sofkau.model.user.User;
import co.com.sofkau.model.user.values.Email;
import co.com.sofkau.model.user.values.Image;
import co.com.sofkau.model.user.values.Name;
import co.com.sofkau.model.user.values.Role;
import co.com.sofkau.mongo.helper.MapperGeneric;
import co.com.sofkau.mongo.user.collection.UserDocument;
import co.com.sofkau.mongo.user.collection.value.EmailDocumentValue;
import co.com.sofkau.mongo.user.collection.value.ImageDocumentValue;
import co.com.sofkau.mongo.user.collection.value.NameDocumentValue;
import co.com.sofkau.mongo.user.collection.value.RoleDocumentValue;
import org.springframework.stereotype.Component;

@Component
public class MapperUserEntity implements MapperGeneric<User, UserDocument> {
    @Override
    public User toEntity(UserDocument entity) {
        return new User(
                entity.getId(),
                new Name(entity.getName().getValue()),
                new Email(entity.getEmail().getValue()),
                new Role(Role.Roles.valueOf(entity.getRole().getValue())),
                new Image(entity.getImage().getUrl())
        );
    }

    @Override
    public UserDocument toData(User model) {
        return UserDocument.builder()
                .id(model.id())
                .name(new NameDocumentValue(model.name()))
                .email(new EmailDocumentValue(model.email()))
                .role(new RoleDocumentValue(model.role().name()))
                .image(new ImageDocumentValue(model.image()))
                .build();
    }
}
