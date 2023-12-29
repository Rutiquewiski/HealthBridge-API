package br.rutiquewiski.HealthBridge.infra.errors;

public class LoginDataException extends Throwable{

    public LoginDataException(String  message){
        super(message);
    }
}
