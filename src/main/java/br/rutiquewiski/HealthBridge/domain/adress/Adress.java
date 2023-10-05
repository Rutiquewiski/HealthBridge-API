package br.rutiquewiski.HealthBridge.domain.adress;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Adress {

    private String streetAddress;
    private String neighborhood;
    private String postalCode;
    private String number;
    private String complement;
    private String city;
    private String state;

    public Adress(AdressDTO adressDTO) {
        this.streetAddress = adressDTO.streetAddress();
        this.neighborhood = adressDTO.neighborhood();
        this.postalCode = adressDTO.postalCode();
        this.number = adressDTO.number();
        this.complement = adressDTO.complement();
        this.city = adressDTO.city();
        this.state = adressDTO.state();
    }
}
