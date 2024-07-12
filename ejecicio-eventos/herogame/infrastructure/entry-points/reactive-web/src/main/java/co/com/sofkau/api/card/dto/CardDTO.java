package co.com.sofkau.api.card.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CardDTO {
    private String id;
    private String name;
    private Integer power;
    private Set<String> features;
    private String imageUrl;
}
