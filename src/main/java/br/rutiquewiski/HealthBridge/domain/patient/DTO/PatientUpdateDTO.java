package br.rutiquewiski.HealthBridge.domain.patient.DTO;

import br.rutiquewiski.HealthBridge.domain.adress.AddressDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;



public record PatientUpdateDTO(

        String name,

        @Email
        String email,

        String phone,

        String document,
        @Valid
        AddressDTO address,
        String medicalHistory
) {
}
