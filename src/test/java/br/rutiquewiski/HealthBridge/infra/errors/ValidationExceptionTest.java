package br.rutiquewiski.HealthBridge.infra.errors;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidationExceptionTest {

    @Test
    void validationExceptionMessage() {
        // Arrange
        String errorMessage = "Validation failed.";

        // Act
        ValidationException validationException = new ValidationException(errorMessage);

        // Assert
        assertEquals(errorMessage, validationException.getMessage());
    }
}
