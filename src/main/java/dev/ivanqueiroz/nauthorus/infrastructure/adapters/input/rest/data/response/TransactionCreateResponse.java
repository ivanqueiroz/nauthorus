package dev.ivanqueiroz.nauthorus.infrastructure.adapters.input.rest.data.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.Date;

@Builder
public record TransactionCreateResponse(
        String transactionId,
        Long userId,
        String currencyOrigin,
        String currencyDestiny,
        BigDecimal amount,
        BigDecimal result,
        BigDecimal currencyRate,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        Date date
) {
}
