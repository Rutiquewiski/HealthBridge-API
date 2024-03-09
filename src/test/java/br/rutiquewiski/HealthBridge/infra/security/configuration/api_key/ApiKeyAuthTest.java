package br.rutiquewiski.HealthBridge.infra.security.configuration.api_key;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class ApiKeyAuthTest {

    @Test
    void testGetCredentials() {
        ApiKeyAuth apiKeyAuth = new ApiKeyAuth("apiKey", Collections.emptyList());
        assertNull(apiKeyAuth.getCredentials());
    }

    @Test
    void testGetPrincipal() {
        String apiKey = "apiKey";
        ApiKeyAuth apiKeyAuth = new ApiKeyAuth(apiKey, Collections.emptyList());
        assertEquals(apiKey, apiKeyAuth.getPrincipal());
    }

    @Test
    void testIsAuthenticated() {
        String apiKey = "apiKey";
        ApiKeyAuth apiKeyAuth = new ApiKeyAuth(apiKey, Collections.emptyList());
        assertTrue(apiKeyAuth.isAuthenticated());
    }
}
