package co.com.sofkau.api.user.dto;

import co.com.sofkau.model.user.values.Email;
import co.com.sofkau.model.user.values.Image;
import co.com.sofkau.model.user.values.Name;
import co.com.sofkau.model.user.values.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UserDTO {

    private String id;
    private String name;
    private String email;
    private String role;
    private String image;
}
