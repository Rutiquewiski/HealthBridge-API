package br.rutiquewiski.HealthBridge.infra.security.controller;

import br.rutiquewiski.HealthBridge.infra.security.domain.DTO.UserRegistrationDTO;
import br.rutiquewiski.HealthBridge.infra.security.domain.DTO.UserUpdateDTO;
import br.rutiquewiski.HealthBridge.infra.security.domain.UserAuth;
import br.rutiquewiski.HealthBridge.infra.security.repositories.UserAuthRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserAuthControllerTest {

    @Mock
    private UserAuthRepository userAuthRepository;

    @InjectMocks
    private UserAuthController userAuthController;

    @Test
    public void testRegisterUser_Valid() {
        // Mock repository save
        when(userAuthRepository.save(any())).thenAnswer(invocation -> {
            UserAuth user = invocation.getArgument(0);
            user.setId(1); // Set mock ID
            return user;
        });

        // Valid DTO
        UserRegistrationDTO validDTO = new UserRegistrationDTO("John Doe", "john.doe", "password");

        // Mocked UriComponentsBuilder
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();

        // Test controller method
        ResponseEntity<?> response = userAuthController.registerUser(validDTO, uriComponentsBuilder);

        // Assertions
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody() instanceof URI);
        assertEquals("/api/user/1", ((URI) response.getBody()).getPath());
    }

    @Test
    public void testUpdateUser_Valid() {
        // Mock repository find by ID
        UserAuth existingUser = new UserAuth();
        when(userAuthRepository.findByIdAndActiveTrue(1)).thenReturn(existingUser);

        // Valid DTO
        UserUpdateDTO validDTO = new UserUpdateDTO("John Doe", "john.doe");

        // Test controller method
        ResponseEntity<?> response = userAuthController.updateUser(validDTO, 1);

        // Assertions
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User information updated", response.getBody());
    }

    @Test
    public void testDeleteUser_Valid() {
        // Mock repository find by ID
        UserAuth existingUser = new UserAuth();
        when(userAuthRepository.findByIdAndActiveTrue(1)).thenReturn(existingUser);

        // Test controller method
        ResponseEntity<?> response = userAuthController.deleteUser(1);

        // Assertions
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User deleted", response.getBody());
    }
}

