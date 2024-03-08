package br.rutiquewiski.HealthBridge.infra.security.controller;

import br.rutiquewiski.HealthBridge.infra.errors.LoginDataException;
import br.rutiquewiski.HealthBridge.infra.security.controller.AuthenticationController;
import br.rutiquewiski.HealthBridge.infra.security.domain.DTO.AuthenticationDTO;
import br.rutiquewiski.HealthBridge.infra.security.service.TokenService;
import br.rutiquewiski.HealthBridge.infra.security.token.ResponseTokenDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthenticationControllerTest {

    @Mock
    private TokenService tokenServiceMock;

    @Mock
    private AuthenticationManager authenticationManagerMock;

    @InjectMocks
    private AuthenticationController authenticationController;

    public AuthenticationControllerTest() {
        MockitoAnnotations.initMocks(this); // Initialize mocks
    }

    @Test
    void login_ValidCredentials_ReturnsToken() throws LoginDataException {
        // Arrange
        String username = "validUsername";
        String password = "validPassword";
        AuthenticationDTO authenticationDTO = new AuthenticationDTO(username, password);
        String generatedToken = "generatedToken";

        when(authenticationManagerMock.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(new UsernamePasswordAuthenticationToken(null, null));
        when(tokenServiceMock.generateToken(any())).thenReturn(generatedToken);

        // Act
        ResponseEntity<ResponseTokenDTO> responseEntity = authenticationController.login(authenticationDTO);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(generatedToken, responseEntity.getBody().token());
    }

    @Test
    void login_InvalidCredentials_ThrowsException() {
        // Arrange
        String username = "invalidUsername";
        String password = "invalidPassword";
        AuthenticationDTO authenticationDTO = new AuthenticationDTO(username, password);

        when(authenticationManagerMock.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new RuntimeException("Authentication failed"));

        // Act and Assert
        assertThrows(LoginDataException.class, () -> authenticationController.login(authenticationDTO));
    }
}
