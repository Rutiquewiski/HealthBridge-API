package br.rutiquewiski.HealthBridge.domain.professional.doctor.DTO;

import br.rutiquewiski.HealthBridge.domain.adress.AddressDTO;
import br.rutiquewiski.HealthBridge.domain.professional.doctor.MedicalSpecialty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DoctorRegistrationDTO(
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
        List<MedicalSpecialty> specialties
) {
}
