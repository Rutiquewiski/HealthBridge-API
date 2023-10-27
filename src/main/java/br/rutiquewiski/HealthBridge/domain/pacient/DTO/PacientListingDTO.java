package br.rutiquewiski.HealthBridge.domain.pacient.DTO;

import br.rutiquewiski.HealthBridge.domain.pacient.Pacient;

public record PacientListingDTO(
        String name,
        String email,
        String phone,
        String document
) {
    public PacientListingDTO(Pacient pacient) {
        this(pacient.getName(), pacient.getEmail(), pacient.getPhone(), pacient.getDocument());
    }
}
