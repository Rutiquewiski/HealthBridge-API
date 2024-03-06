package br.rutiquewiski.HealthBridge.domain.adress;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Address {

    private String street_address;
    private String neighborhood;
    private String postal_code;
    private String number;
    private String complement;
    private String city;
    private String state;

    public Address(AddressDTO addressDTO) {
        this.street_address = addressDTO.street_address();
        this.neighborhood = addressDTO.neighborhood();
        this.postal_code = addressDTO.postal_code();
        this.number = addressDTO.number();
        this.complement = addressDTO.complement();
        this.city = addressDTO.city();
        this.state = addressDTO.state();
    }
}
