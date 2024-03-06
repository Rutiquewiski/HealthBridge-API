package br.rutiquewiski.HealthBridge.domain.adress;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressDTO(
        @NotBlank
        String street_address,

        String neighborhood,
        @NotBlank
        @Pattern(regexp = "\\d{8}")  //This is Brazil's postal code pattern
        String postal_code,
        @NotBlank
        String number,
        String complement,
        @NotBlank
        String city,
        @NotBlank
        String state
) {
}
