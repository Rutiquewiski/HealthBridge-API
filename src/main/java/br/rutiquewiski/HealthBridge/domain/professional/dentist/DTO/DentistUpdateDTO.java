package br.rutiquewiski.HealthBridge.domain.professional.dentist.DTO;

import br.rutiquewiski.HealthBridge.domain.adress.AdressDTO;
import br.rutiquewiski.HealthBridge.domain.professional.dentist.DentalSpecialty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;

import java.util.List;

public record DentistUpdateDTO(

        String name,
        @Email
        String email,

        String phone,

        String document,
        @Valid
        AdressDTO adress,
        List<DentalSpecialty> specialties
) {
}
