package pl.coderslab.charity.validations.constrains;

import pl.coderslab.charity.validations.validators.DoublePasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DoublePasswordValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DoublePassword {
    String message() default "{doublePassword.validation.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}