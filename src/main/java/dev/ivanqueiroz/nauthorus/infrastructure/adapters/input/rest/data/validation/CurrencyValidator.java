package dev.ivanqueiroz.nauthorus.infrastructure.adapters.input.rest.data.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Objects;

public class CurrencyValidator implements ConstraintValidator<ValidCurrency, String> {

    @Override
    public boolean isValid(String currency, ConstraintValidatorContext context) {
        if (Objects.nonNull(currency) && !currency.isBlank() && currency.length() == 3) {
            return Arrays.asList("BRL,USD,EUR,JPY".split(",")).contains(currency);
        }
        return false;
    }
}