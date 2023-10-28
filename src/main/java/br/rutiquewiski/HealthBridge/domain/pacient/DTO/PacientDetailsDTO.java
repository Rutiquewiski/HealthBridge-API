package br.rutiquewiski.HealthBridge.domain.pacient.DTO;

import br.rutiquewiski.HealthBridge.domain.adress.Adress;
import br.rutiquewiski.HealthBridge.domain.pacient.Pacient;

public record PacientDetailsDTO(
        Integer id,
        String name,
        String email,
        String phone,
        String document,
        Adress adress
) {
    public PacientDetailsDTO(Pacient pacient) {
        this(pacient.getId(), pacient.getName(), pacient.getEmail(), pacient.getPhone(), pacient.getDocument(), pacient.getAdress());
    }
}
