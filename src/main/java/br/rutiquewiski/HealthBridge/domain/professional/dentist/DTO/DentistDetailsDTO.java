package br.rutiquewiski.HealthBridge.domain.professional.dentist.DTO;

import br.rutiquewiski.HealthBridge.domain.adress.Address;
import br.rutiquewiski.HealthBridge.domain.professional.dentist.DentalSpecialty;
import br.rutiquewiski.HealthBridge.domain.professional.dentist.Dentist;

import java.util.List;

public record DentistDetailsDTO(
        Integer id,
        String name,
        String email,
        String phone,
        String document,
        Address address,
        List<DentalSpecialty> specialties
) {

    public DentistDetailsDTO(Dentist dentist) {
        this(dentist.getId(), dentist.getName(), dentist.getEmail(), dentist.getPhone(), dentist.getDocument(), dentist.getAddress(), dentist.getSpecialties());
    }
}
