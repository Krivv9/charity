package pl.coderslab.charity.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.coderslab.charity.validations.constrains.UniqueEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EditUserDTO {

    private long id;

    private boolean active;

    private String firstName;

    private String lastName;

    @Email
    @NotBlank
    @UniqueEmail
    private String email;



}