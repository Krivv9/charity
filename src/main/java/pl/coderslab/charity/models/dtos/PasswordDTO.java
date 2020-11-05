package pl.coderslab.charity.models.dtos;

import lombok.Getter;
import lombok.Setter;
import pl.coderslab.charity.validations.constrains.DoublePassword;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@DoublePassword
public class PasswordDTO {

    @NotBlank
    private String password;

    @NotBlank
    private String doublePassword;
}
