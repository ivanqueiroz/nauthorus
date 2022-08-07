package dev.ivanqueiroz.nauthorus.model;

import dev.ivanqueiroz.nauthorus.domain.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TransactionTest {

    private Map<String, BigDecimal> rates;

    @BeforeEach
    void prepare() {
        rates = Map.of("BRL", new BigDecimal("6.201951"),
                "JPY", new BigDecimal("128.901692")
        );
    }

    @Test
    void testCalculateResult() {
        var transaction = Transaction.builder()
                .currencyOrigin("BRL")
                .currencyDestiny("JPY")
                .amount(new BigDecimal("5"))
                .build();
        transaction.calculateWithRates(rates);
        BigDecimal expectedJpn = new BigDecimal("103.920277");
        assertEquals(expectedJpn, transaction.getResult());
    }

    @Test
    void testCalculateResultWithoutCurrencyOrigin() {
        var transaction = Transaction.builder()
                .currencyDestiny("JPY")
                .amount(new BigDecimal("5"))
                .build();
        var exception = assertThrows(NullPointerException.class,
                () -> transaction.calculateWithRates(rates), "Currency origin came with valor.");
        assertEquals("Currency origin must be not null", exception.getMessage());
    }

    @Test
    void testCalculateResultWithoutCurrencyDestiny() {
        var transaction = Transaction.builder()
                .currencyOrigin("BRL")
                .amount(new BigDecimal("5"))
                .build();
        var exception = assertThrows(NullPointerException.class,
                () -> transaction.calculateWithRates(rates), "Currency destiny came with valor.");
        assertEquals("Currency destiny must be not null", exception.getMessage());
    }

    @Test
    void testCalculateResultWithoutRates() {
        var transaction = Transaction.builder()
                .currencyOrigin("BRL")
                .amount(new BigDecimal("5"))
                .build();
        var exception = assertThrows(NullPointerException.class,
                () -> transaction.calculateWithRates(null), "Map of rates came with valor.");
        assertEquals("Map of rates must be not null.", exception.getMessage());
    }

    @Test
    void testCalculateRate() {
        var transaction = Transaction.builder()
                .currencyOrigin("BRL")
                .currencyDestiny("JPY")
                .amount(new BigDecimal("5"))
                .build();
        transaction.calculateWithRates(rates);
        assertEquals(new BigDecimal("20.784056"), transaction.getCurrencyRate());
    }
}