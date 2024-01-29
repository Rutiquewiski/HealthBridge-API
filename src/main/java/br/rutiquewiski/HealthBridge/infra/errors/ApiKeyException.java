package br.rutiquewiski.HealthBridge.infra.errors;

public class ApiKeyException extends Throwable{

    public ApiKeyException(){
        super("Invalid API KEY");
    }
}
