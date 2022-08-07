package dev.ivanqueiroz.nauthorus.application.ports.output;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public interface LoadRatesOutputPort {

    Optional<Map<String, BigDecimal>> load(Long userId);

}
