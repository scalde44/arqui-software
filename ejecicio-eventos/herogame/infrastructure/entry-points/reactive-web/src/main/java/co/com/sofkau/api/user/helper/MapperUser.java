package co.com.sofkau.api.user.helper;

import co.com.sofkau.api.helper.MapperGenericDto;
import co.com.sofkau.api.user.dto.UserDTO;
import co.com.sofkau.model.user.User;
import co.com.sofkau.model.user.values.Email;
import co.com.sofkau.model.user.values.Image;
import co.com.sofkau.model.user.values.Name;
import co.com.sofkau.model.user.values.Role;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
public class MapperUser implements MapperGenericDto<UserDTO, User> {
    @Override
    public Function<UserDTO, User> mapToModel(String id) {
        return userDTO -> new User(
                id,
                new Name(userDTO.getName()),
                new Email(userDTO.getEmail()),
                new Role(Role.Roles.valueOf(userDTO.getRole())),
                new Image(userDTO.getImage())
        );
    }

    @Override
    public Function<User, UserDTO> mapToDTO() {
        return entity ->UserDTO.builder()
                .id(entity.id())
                .name(entity.name())
                .email(entity.email())
                .role(entity.role().name())
                .image(entity.image())
                .build();
    }
}
