package br.rutiquewiski.HealthBridge.domain.professional.dentist.DTO;

import br.rutiquewiski.HealthBridge.domain.adress.AddressDTO;
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
        AddressDTO address,
        List<DentalSpecialty> specialties
) {
}
