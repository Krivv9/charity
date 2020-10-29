package pl.coderslab.charity.models.dtos;

import lombok.Data;
import pl.coderslab.charity.validations.constrains.DoublePassword;
import pl.coderslab.charity.validations.constrains.UniqueEmail;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@DoublePassword
public class UserToAddDTO {

    @NotEmpty
    private String name;

    @Email
    @NotEmpty
    @UniqueEmail
    private String email;

    @Size(min = 4)
    @NotEmpty
    private String password;
    private String doublePassword;
}
