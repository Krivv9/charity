package pl.coderslab.charity.validations.validators;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.coderslab.charity.repositories.UserRepository;
import pl.coderslab.charity.validations.constrains.UniqueEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@Slf4j
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    private final UserRepository userRepository;

    public UniqueEmailValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(String valueToValid, ConstraintValidatorContext context) {
        log.info("Attempt to valid unique email: " + valueToValid);
        return !userRepository.existsByEmail(valueToValid);
    }
}
