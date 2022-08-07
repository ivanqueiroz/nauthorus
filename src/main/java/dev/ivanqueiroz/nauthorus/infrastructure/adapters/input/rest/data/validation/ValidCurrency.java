package dev.ivanqueiroz.nauthorus.infrastructure.adapters.input.rest.data.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({ FIELD, ANNOTATION_TYPE, TYPE_USE })
@Constraint(validatedBy = CurrencyValidator.class)
public @interface ValidCurrency {
    String message() default "Currency value invalid. Permited values: BRL, USD, EUR, JPY";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
