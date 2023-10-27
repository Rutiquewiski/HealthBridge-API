package br.rutiquewiski.HealthBridge.domain.professional.Dentist.DTO;

import br.rutiquewiski.HealthBridge.domain.professional.Dentist.DentalSpecialty;
import br.rutiquewiski.HealthBridge.domain.professional.Dentist.Dentist;

import java.util.List;

public record DentistListingDTO(
        String name,
        String email,
        String phone,
        String document,
        List<DentalSpecialty> specialties
) {

    public DentistListingDTO(Dentist dentist) {
        this(dentist.getName(), dentist.getEmail(), dentist.getPhone(), dentist.getDocument(), dentist.getSpecialties());
    }
}
