package br.rutiquewiski.HealthBridge.domain.pacient.DTO;

import br.rutiquewiski.HealthBridge.domain.adress.AdressDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;



public record PacientUpdateDTO(

        String name,

        @Email
        String email,

        String phone,

        String document,
        @Valid
        AdressDTO adress
) {
}
