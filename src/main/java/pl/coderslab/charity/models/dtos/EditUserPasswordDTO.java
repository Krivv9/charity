package pl.coderslab.charity.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.coderslab.charity.validations.constrains.DoublePassword;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DoublePassword
public class EditUserPasswordDTO extends PasswordDTO{

    private long id;

}