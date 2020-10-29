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

    @NotEmpty(message = "*Please write your name")
    private String name;

    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    @UniqueEmail
    private String email;

    @Size(min = 4, message = "*Your password must have at least 4 characters")
    @NotEmpty(message = "*Please provide your password")
    private String password;
    private String doublePassword;

    private Boolean active;
}
