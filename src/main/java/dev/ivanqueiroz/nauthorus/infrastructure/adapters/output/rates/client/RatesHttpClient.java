package dev.ivanqueiroz.nauthorus.infrastructure.adapters.output.rates.client;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.client.annotation.Client;
import org.reactivestreams.Publisher;

import static io.micronaut.http.HttpHeaders.ACCEPT;
import static io.micronaut.http.HttpHeaders.USER_AGENT;

@Client("${exchange-rate-api.baseUrl}")
@Header(name = USER_AGENT, value = "Micronaut HTTP Client")
@Header(name = ACCEPT, value = "application/vnd.github.v3+json, application/json")
public interface RatesHttpClient {

    @Get("/latest?access_key=${exchange-rate-api.api-key}&symbols=BRL,USD,EUR,JPY&format=${exchange-rate-api.format}")
    Publisher<RatesResponse> fetchRates();
}
