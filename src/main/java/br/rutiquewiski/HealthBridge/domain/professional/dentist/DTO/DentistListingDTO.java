package br.rutiquewiski.HealthBridge.domain.professional.dentist.DTO;

import br.rutiquewiski.HealthBridge.domain.professional.dentist.DentalSpecialty;
import br.rutiquewiski.HealthBridge.domain.professional.dentist.Dentist;

import java.util.List;

public record DentistListingDTO(
        int id,
        String name,
        String email,
        String phone,
        String document,
        List<DentalSpecialty> specialties
) {

    public DentistListingDTO(Dentist dentist) {
        this(dentist.getId(), dentist.getName(), dentist.getEmail(), dentist.getPhone(), dentist.getDocument(), dentist.getSpecialties());
    }
}
