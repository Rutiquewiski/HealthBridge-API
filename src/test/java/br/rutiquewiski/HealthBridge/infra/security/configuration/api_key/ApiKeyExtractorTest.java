package br.rutiquewiski.HealthBridge.infra.security.configuration.api_key;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ApiKeyExtractorTest {

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    @Autowired
    private ApiKeyExtractor apiKeyExtractor;

    @Value("${api.security.key.name}")
    private String name;
    @Value("${api.security.key.value}")
    private String value;

    @Test
    void extract_ValidApiKey_ReturnsAuthentication() {
        // Arrange
        when(request.getHeader(name)).thenReturn(value);

        // Act
        Optional<Authentication> authentication = apiKeyExtractor.extract(request);

        // Assert
        assertTrue(authentication.isPresent());
        assertInstanceOf(ApiKeyAuth.class, authentication.get());
    }

    @Test
    void extract_InvalidApiKey_ReturnsEmptyOptional() {
        // Arrange
        when(request.getHeader(name)).thenReturn("invalidApiKey");

        // Act
        Optional<Authentication> authentication = apiKeyExtractor.extract(request);

        // Assert
        assertTrue(authentication.isEmpty());
    }

    @Test
    void extract_MissingApiKey_ReturnsEmptyOptional() {
        // Arrange
        when(request.getHeader(name)).thenReturn(null);

        // Act
        Optional<Authentication> authentication = apiKeyExtractor.extract(request);

        // Assert
        assertTrue(authentication.isEmpty());
    }
}
