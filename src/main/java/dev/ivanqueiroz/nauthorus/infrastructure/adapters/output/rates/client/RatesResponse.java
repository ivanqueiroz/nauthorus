package dev.ivanqueiroz.nauthorus.infrastructure.adapters.output.rates.client;

import io.micronaut.core.annotation.Introspected;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@Introspected
@Getter
@Setter
@NoArgsConstructor
public class RatesResponse {
    private boolean success;
    private int timestamp;
    private String base;
    private String date;
    private Map<String, BigDecimal> rates;
}
