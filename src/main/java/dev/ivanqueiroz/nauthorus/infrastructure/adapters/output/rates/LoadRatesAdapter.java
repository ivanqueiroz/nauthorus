package dev.ivanqueiroz.nauthorus.infrastructure.adapters.output.rates;

import dev.ivanqueiroz.nauthorus.application.ports.output.LoadRatesOutputPort;
import dev.ivanqueiroz.nauthorus.infrastructure.adapters.output.rates.client.RatesHttpClient;
import io.micronaut.cache.annotation.CacheConfig;
import io.micronaut.cache.annotation.Cacheable;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Singleton
@RequiredArgsConstructor
@CacheConfig("rates")
public class LoadRatesAdapter implements LoadRatesOutputPort {

    private final RatesHttpClient ratesHttpClient;

    @Override
    @Cacheable
    public Optional<Map<String, BigDecimal>> load(Long userId) {
        var ratesResponse = Mono.from(ratesHttpClient.fetchRates()).block();
        if (Objects.isNull(ratesResponse) || !ratesResponse.isSuccess()) {
            return Optional.empty();
        }
        return Optional.of(ratesResponse.getRates());
    }
}
