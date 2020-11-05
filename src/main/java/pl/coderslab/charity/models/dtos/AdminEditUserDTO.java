package pl.coderslab.charity.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminEditUserDTO {

    private long id;

    private boolean active;

    private String firstName;

    private String lastName;


}