package br.rutiquewiski.HealthBridge.domain.professional.dentist.DTO;

import br.rutiquewiski.HealthBridge.domain.adress.AdressDTO;
import br.rutiquewiski.HealthBridge.domain.professional.dentist.DentalSpecialty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DentistRegistrationDTO(
        @NotNull
        String name,
        @NotNull
        @Email
        String email,
        @NotNull
        String phone,
        @NotNull
        String document,
        @Valid
        AdressDTO adress,
        List<DentalSpecialty>specialties
) {
}
