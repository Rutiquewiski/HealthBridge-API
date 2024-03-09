package br.rutiquewiski.HealthBridge.infra.errors;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginDataExceptionTest {

    @Test
    void loginDataExceptionMessage() {
        // Arrange
        String errorMessage = "Invalid login data.";

        // Act
        LoginDataException exception = new LoginDataException(errorMessage);

        // Assert
        assertEquals(errorMessage, exception.getMessage());
    }
}

