package br.rutiquewiski.HealthBridge.domain.adress;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AdressDTO(
        @NotBlank
        String streetAddress,
        @NotBlank
        String neighborhood,
        @NotBlank
        @Pattern(regexp = "\\d{8}")  //This is Brazil's postal code pattern
        String postalCode,
        @NotBlank
        String number,
        @NotBlank
        String complement,
        String city,
        String state
) {
}
