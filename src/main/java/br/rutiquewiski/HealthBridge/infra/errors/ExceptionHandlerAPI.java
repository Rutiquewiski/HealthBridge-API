package br.rutiquewiski.HealthBridge.infra.errors;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAPI {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {

        var errors = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(ValidationErrorInfo::new).toList());

        //This method handles the exception that happens when the API receives and DTO with an invalid or null field
    }

    private record ValidationErrorInfo(String field, String message){

        public ValidationErrorInfo(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    };

}
