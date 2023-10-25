package br.rutiquewiski.HealthBridge.domain.professional.Dentist.DTO;

import br.rutiquewiski.HealthBridge.domain.adress.Adress;
import br.rutiquewiski.HealthBridge.domain.professional.Dentist.DentalSpecialty;
import br.rutiquewiski.HealthBridge.domain.professional.Dentist.Dentist;

import java.util.List;

public record DentistDetailsDTO(
        Integer id,
        String name,
        String email,
        String phone,
        String document,
        Adress adress,
        List<DentalSpecialty> specialties
) {

    public DentistDetailsDTO(Dentist dentist) {
        this(dentist.getId(), dentist.getName(), dentist.getEmail(), dentist.getPhone(), dentist.getDocument(), dentist.getAdress(), dentist.getSpecialties());
    }
}
