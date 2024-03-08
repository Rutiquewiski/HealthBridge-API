package br.rutiquewiski.HealthBridge.infra.security.service;

import br.rutiquewiski.HealthBridge.infra.security.domain.UserAuth;
import br.rutiquewiski.HealthBridge.infra.security.repositories.UserAuthRepository;
import br.rutiquewiski.HealthBridge.infra.security.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthenticationServiceTest {

    @Mock
    private UserAuthRepository userAuthRepositoryMock;

    @InjectMocks
    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this); // Initialize the mocks before each test method
    }

    @Test
    void loadUserByUsername_ExistingUsername_ReturnsUserDetails() {
        // Arrange
        String username = "existingUsername";
        UserAuth userAuth = new UserAuth(1, "John Doe", username, "password", true);
        when(userAuthRepositoryMock.findByUsername(username)).thenReturn(userAuth);

        // Act
        UserDetails userDetails = authenticationService.loadUserByUsername(username);

        // Assert
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
    }
}

