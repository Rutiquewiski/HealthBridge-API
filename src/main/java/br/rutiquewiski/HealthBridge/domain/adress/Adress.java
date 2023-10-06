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

    private String street_address;
    private String neighborhood;
    private String postal_code;
    private String number;
    private String complement;
    private String city;
    private String state;

    public Adress(AdressDTO adressDTO) {
        this.street_address = adressDTO.street_address();
        this.neighborhood = adressDTO.neighborhood();
        this.postal_code = adressDTO.postal_code();
        this.number = adressDTO.number();
        this.complement = adressDTO.complement();
        this.city = adressDTO.city();
        this.state = adressDTO.state();
    }
}
