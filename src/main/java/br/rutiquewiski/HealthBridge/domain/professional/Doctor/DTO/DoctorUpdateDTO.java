package br.rutiquewiski.HealthBridge.domain.professional.Doctor.DTO;

import br.rutiquewiski.HealthBridge.domain.adress.AdressDTO;
import br.rutiquewiski.HealthBridge.domain.professional.Doctor.MedicalSpecialty;
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
        AdressDTO adress,
        List<MedicalSpecialty> specialties
) {
}
