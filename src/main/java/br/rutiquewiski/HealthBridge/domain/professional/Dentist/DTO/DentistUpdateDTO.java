package br.rutiquewiski.HealthBridge.domain.professional.Dentist.DTO;

import br.rutiquewiski.HealthBridge.domain.adress.AdressDTO;
import br.rutiquewiski.HealthBridge.domain.professional.Dentist.DentalSpecialty;

import java.util.List;

public record DentistUpdateDTO(

        String name,

        String email,

        String phone,

        String document,

        AdressDTO adress,
        List<DentalSpecialty> specialties
) {
}
