package br.rutiquewiski.HealthBridge.domain.professional.doctor.DTO;

import br.rutiquewiski.HealthBridge.domain.adress.AddressDTO;
import br.rutiquewiski.HealthBridge.domain.professional.doctor.MedicalSpecialty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;

import java.util.List;

public record DoctorUpdateDTO(
        String name,
        @Email
        String email,

        String phone,

        String document,
        @Valid
        AddressDTO address,
        List<MedicalSpecialty> specialties
) {
}
