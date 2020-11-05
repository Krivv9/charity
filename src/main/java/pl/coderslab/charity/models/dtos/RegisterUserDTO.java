package pl.coderslab.charity.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import pl.coderslab.charity.validations.constrains.UniqueEmail;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@Builder
public class RegisterUserDTO extends PasswordDTO {
    public RegisterUserDTO (){
        super();
    }
    private long id;

    @UniqueEmail
    @NotBlank
    private String email;


}
