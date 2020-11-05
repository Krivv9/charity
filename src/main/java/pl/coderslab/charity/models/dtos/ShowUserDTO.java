package pl.coderslab.charity.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ShowUserDTO {
    private long id;

    private String email;

    private String firstName;

    private String lastName;

    private boolean active;
}
