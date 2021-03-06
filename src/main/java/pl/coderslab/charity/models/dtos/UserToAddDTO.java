package pl.coderslab.charity.models.dtos;

import lombok.Data;
import pl.coderslab.charity.validations.constrains.DoublePassword;
import pl.coderslab.charity.validations.constrains.UniqueEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@DoublePassword
public class UserToAddDTO {

    private long id;

    private String firstName;

    private String lastName;

    private boolean active;

    @NotBlank
    private String name;

    @Email
    @NotBlank
    @UniqueEmail
    private String email;

    @Size(min = 4)
    @NotBlank
    private String password;

    @NotBlank
    private String doublePassword;
}
