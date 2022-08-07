package dev.ivanqueiroz.nauthorus.infrastructure.adapters.input.rest.data.request;

import dev.ivanqueiroz.nauthorus.infrastructure.adapters.input.rest.data.validation.ValidCurrency;
import io.micronaut.core.annotation.Introspected;
import lombok.Builder;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Builder
@Introspected
public record TransactionCreateRequest(
        @NotNull(message = "User ID is mandatory.")
        @PositiveOrZero(message = "User ID is mandatory.")
        Long userId,

        @ValidCurrency
        @NotBlank(message = "Original currency is mandatory (Valid options: BRL, USD, EUR, JPY)")
        String currencyOrigin,

        @ValidCurrency
        @NotBlank(message = "Destiny currency is mandatory (Valid options: BRL, USD, EUR, JPY)")
        String currencyDestiny,

        @NotNull(message = "Amount is mandatory.")
        @PositiveOrZero(message = "Amount is mandatory.")
        @Digits(integer = 6, fraction = 2, message = "Amount out of range.")
        BigDecimal amount
) {
}
