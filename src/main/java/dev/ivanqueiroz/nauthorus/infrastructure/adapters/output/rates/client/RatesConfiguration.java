package dev.ivanqueiroz.nauthorus.infrastructure.adapters.output.rates.client;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.context.annotation.Requires;
import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(RatesConfiguration.PREFIX)
@Requires(property = RatesConfiguration.PREFIX)
@Getter
@Setter
public class RatesConfiguration {

    public static final String PREFIX = "exchange-rate-api";

    private String baseUrl;
    private String apiKey;
    private String format;

}
