package br.rutiquewiski.HealthBridge.infra.errors;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ExceptionHandlerAPITest {

    @Test
    void handleMethodArgumentNotValidException() {
        // Arrange
        BindingResult bindingResult = new org.springframework.validation.BeanPropertyBindingResult(null, "objectName");
        bindingResult.addError(new FieldError("objectName", "fieldName", "Field error message"));
        MethodArgumentNotValidException exception = new MethodArgumentNotValidException(null, bindingResult);
        ExceptionHandlerAPI handler = new ExceptionHandlerAPI();

        // Act
        ResponseEntity<?> responseEntity = handler.handleMethodArgumentNotValidException(exception);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertInstanceOf(List.class, responseEntity.getBody());
    }

    @Test
    void handleValidationException() {
        // Arrange
        String errorMessage = "Validation failed.";
        ValidationException exception = new ValidationException(errorMessage);
        ExceptionHandlerAPI handler = new ExceptionHandlerAPI();

        // Act
        ResponseEntity<?> responseEntity = handler.handleValidationException(exception);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(errorMessage, responseEntity.getBody());
    }

    // Similarly, write tests for handleSQLIntegrityConstraintViolationException and handleLoginException methods
}
