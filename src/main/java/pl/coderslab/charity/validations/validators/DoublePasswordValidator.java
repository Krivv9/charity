package pl.coderslab.charity.validations.validators;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.models.dtos.PasswordDTO;
import pl.coderslab.charity.models.dtos.UserToAddDTO;
import pl.coderslab.charity.validations.constrains.DoublePassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@Slf4j
public class DoublePasswordValidator implements ConstraintValidator<DoublePassword, PasswordDTO> {

    @Override
    public boolean isValid(PasswordDTO passwordDTO, ConstraintValidatorContext context) {
        String password = passwordDTO.getPassword();
        String doublePassword = passwordDTO.getDoublePassword();
        log.info("Attempt to valid if password is double");
        boolean isPassDouble = password.equals(doublePassword);

        if (!isPassDouble) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("{doublePassword.validation.message}")
                    .addPropertyNode("doublePassword")
                    .addConstraintViolation();
        }
        return isPassDouble;
    }
}
