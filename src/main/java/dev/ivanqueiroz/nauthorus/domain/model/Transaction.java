package dev.ivanqueiroz.nauthorus.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    private Long userId;

    private String currencyOrigin;

    private String currencyDestiny;

    private BigDecimal amount;

    private BigDecimal currencyRate;

    private BigDecimal result;

    private BigDecimal rateOrigin;

    private BigDecimal rateDestiny;

    private Date date;

    public void calculateWithRates(Map<String, BigDecimal> ratesMap) {
        fillRates(ratesMap);
        date = Date.from(ZonedDateTime.now(ZoneId.of("UTC")).toInstant());
        calculateCurrencyRate();
        calculateResult();
    }

    private void fillRates(Map<String, BigDecimal> ratesMap) {
        Objects.requireNonNull(ratesMap, "Map of rates must be not null.");
        validateCurrencies();
        rateOrigin = ratesMap.get(currencyOrigin);
        rateDestiny = ratesMap.get(currencyDestiny);
    }

    private void calculateResult() {
        if (isRatesWithValues()) {
            this.result = amount.multiply(rateDestiny).divide(rateOrigin, 6, RoundingMode.UP);
        }
    }

    private boolean isRatesWithValues() {
        validateRates();
        return !rateOrigin.equals(BigDecimal.ZERO) && !rateDestiny.equals(BigDecimal.ZERO);
    }

    private void calculateCurrencyRate() {
        validateCurrencies();
        this.currencyRate = rateDestiny.divide(rateOrigin, 6, RoundingMode.UP);

    }

    private void validateCurrencies() {
        Objects.requireNonNull(currencyOrigin, "Currency origin must be not null");
        Objects.requireNonNull(currencyDestiny, "Currency destiny must be not null");
    }

    private void validateRates() {
        Objects.requireNonNull(rateOrigin, "Rate origin must be not null");
        Objects.requireNonNull(rateDestiny, "Rate destiny must be not null");
    }
}
