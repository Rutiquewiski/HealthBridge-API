package br.rutiquewiski.HealthBridge.domain.patient.DTO;

import br.rutiquewiski.HealthBridge.domain.adress.AddressDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record PatientRegistrationDTO(
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
        AddressDTO address,
        String medicalHistory
) {
}
