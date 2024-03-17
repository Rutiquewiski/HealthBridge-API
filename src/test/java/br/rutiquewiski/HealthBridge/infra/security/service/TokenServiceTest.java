package br.rutiquewiski.HealthBridge.infra.security.service;

import br.rutiquewiski.HealthBridge.infra.security.domain.UserAuth;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TokenServiceTest {

    @InjectMocks
    private TokenService tokenService;

    @Mock
    private Algorithm algorithm;

    @Mock
    private JWTVerifier jwtVerifier;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(tokenService, "secret", "testSecret"); // Set the secret field using reflection
    }

    @Test
    void testGenerateToken() {
        // Arrange
        UserAuth userAuth = new UserAuth(1, "testUser", "testUser", "testPassword", true);
        String expectedPrefix = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9"; // The prefix of JWT token

        // Mocking behavior
        when(algorithm.getName()).thenReturn("testAlgorithm");

        // Act
        String generatedToken = tokenService.generateToken(userAuth);

        // Assert
        assertEquals(expectedPrefix, generatedToken.split("\\.")[0]);
    }

    @Test
    void testGetSubject() {
        // Arrange
        UserAuth userAuth = new UserAuth(1, "testUser", "testUser", "testPassword", true);
        String generatedToken = tokenService.generateToken(userAuth);
        String expectedSubject = "testUser";

        // Mocking behavior
        DecodedJWT decodedJWT = mock(DecodedJWT.class);
        when(decodedJWT.getSubject()).thenReturn(expectedSubject);
        when(jwtVerifier.verify(anyString())).thenReturn(decodedJWT);

        // Act
        String subject = tokenService.getSubject(generatedToken);

        // Assert
        assertEquals(expectedSubject, subject);
    }

    @Test
    void testExpireDate() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        Instant expectedExpireDate = now.plusHours(24).toInstant(ZoneOffset.of("-03:00"));

        // Act
        Instant expireDate = tokenService.expireDate();

        // Assert
        assertEquals(expectedExpireDate, expireDate);
    }
}



