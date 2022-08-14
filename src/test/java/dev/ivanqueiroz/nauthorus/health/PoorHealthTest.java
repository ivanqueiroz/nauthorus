package dev.ivanqueiroz.nauthorus.health;

import io.micronaut.context.annotation.Property;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

@Property(name = "endpoints.health.disk-space.threshold", value = "999999999999999999")
@MicronautTest
class PoorHealthTest {

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void healthEndpointExposesOutOfDiscSpace() {

        Executable e = () -> client.toBlocking().retrieve(HttpRequest.GET("/health"));
        HttpClientResponseException thrown = assertThrows(HttpClientResponseException.class, e);

        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, thrown.getStatus());
        assertTrue(thrown.getResponse().getBody(String.class).orElse("").contains("DOWN"));
    }
}
