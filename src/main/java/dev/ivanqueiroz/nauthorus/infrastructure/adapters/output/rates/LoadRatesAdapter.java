package dev.ivanqueiroz.nauthorus.infrastructure.adapters.output.rates;

import dev.ivanqueiroz.nauthorus.application.ports.output.LoadRatesOutputPort;
import dev.ivanqueiroz.nauthorus.infrastructure.adapters.output.rates.client.RatesHttpClient;
import io.micronaut.cache.CacheManager;
import io.micronaut.cache.SyncCache;
import io.micronaut.cache.annotation.CacheConfig;
import io.micronaut.cache.annotation.Cacheable;
import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Singleton
@RequiredArgsConstructor
@CacheConfig("rates")
@Slf4j
public class LoadRatesAdapter implements LoadRatesOutputPort {

    private final RatesHttpClient ratesHttpClient;

    private final CacheManager<?> cacheManager;

    @Override
    @Cacheable
    public Optional<Map<String, BigDecimal>> load(Long userId) {
        var ratesResponse = Mono.from(ratesHttpClient.fetchRates()).block();
        if (Objects.isNull(ratesResponse) || !ratesResponse.isSuccess()) {
            return Optional.empty();
        }
        return Optional.of(ratesResponse.getRates());
    }

    @Scheduled(fixedRate = "1m")
    public void cleanCache() {
        log.info("Cleaning caches.");
        SyncCache<?> load = cacheManager.getCache("rates");
        load.invalidateAll();
    }


}
