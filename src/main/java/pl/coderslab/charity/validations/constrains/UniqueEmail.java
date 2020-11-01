package pl.coderslab.charity.validations.constrains;

import pl.coderslab.charity.validations.validators.UniqueEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueEmailValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {
    String message() default "{uniqueEmail.validation.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}