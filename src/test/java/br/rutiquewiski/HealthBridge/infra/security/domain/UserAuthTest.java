package br.rutiquewiski.HealthBridge.infra.security.domain;

import br.rutiquewiski.HealthBridge.infra.security.domain.DTO.UserRegistrationDTO;
import br.rutiquewiski.HealthBridge.infra.security.domain.DTO.UserUpdateDTO;
import br.rutiquewiski.HealthBridge.infra.security.domain.UserAuth;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Collection;
import static org.junit.jupiter.api.Assertions.*;

public class UserAuthTest {

    @Test
    void getUsername_ReturnsUsername() {
        // Arrange
        UserRegistrationDTO registrationDTO = new UserRegistrationDTO("John Doe", "johndoe", "password");
        UserAuth user = new UserAuth(registrationDTO);

        // Act
        String username = user.getUsername();

        // Assert
        assertEquals("johndoe", username);
    }

    @Test
    void getPassword_ReturnsEncodedPassword() {
        // Arrange
        UserRegistrationDTO registrationDTO = new UserRegistrationDTO("John Doe", "johndoe", "password");
        UserAuth user = new UserAuth(registrationDTO);

        // Act
        String password = user.getPassword();

        // Assert
        assertTrue(new BCryptPasswordEncoder().matches("password", password));
    }

    @Test
    void getAuthorities_ReturnsUserRole() {
        // Arrange
        UserRegistrationDTO registrationDTO = new UserRegistrationDTO("John Doe", "johndoe", "password");
        UserAuth user = new UserAuth(registrationDTO);

        // Act
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        // Assert
        assertEquals(1, authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_USER")));
    }

    // Similar tests for other UserDetails interface methods (isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled)

    @Test
    void updateInfo_ModifiesNameAndUsername() {
        // Arrange
        UserAuth user = new UserAuth();
        UserUpdateDTO updateDTO = new UserUpdateDTO("Updated Name", "updatedUsername");

        // Act
        user.updateInfo(updateDTO);

        // Assert
        assertEquals("Updated Name", user.getName());
        assertEquals("updatedUsername", user.getUsername());
    }

    @Test
    void safeDelete_SetsActiveToFalse() {
        // Arrange
        UserAuth user = new UserAuth();

        // Act
        user.safeDelete();

        // Assert
        assertFalse(user.isActive());
    }
}

