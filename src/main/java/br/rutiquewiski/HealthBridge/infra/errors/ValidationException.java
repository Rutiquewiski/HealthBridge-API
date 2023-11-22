package br.rutiquewiski.HealthBridge.infra.errors;

public class ValidationException extends Throwable {
    public ValidationException(String message) {

        super(message);
    }
}
