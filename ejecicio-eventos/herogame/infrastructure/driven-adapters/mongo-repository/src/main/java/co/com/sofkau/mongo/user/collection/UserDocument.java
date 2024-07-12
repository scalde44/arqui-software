package co.com.sofkau.mongo.user.collection;

import co.com.sofkau.mongo.user.collection.value.EmailDocumentValue;
import co.com.sofkau.mongo.user.collection.value.ImageDocumentValue;
import co.com.sofkau.mongo.user.collection.value.NameDocumentValue;
import co.com.sofkau.mongo.user.collection.value.RoleDocumentValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Document(value = "user")
public class UserDocument {
    @Id

    private String id;
    private NameDocumentValue name;
    private EmailDocumentValue email;
    private RoleDocumentValue role;
    private ImageDocumentValue image;
}
