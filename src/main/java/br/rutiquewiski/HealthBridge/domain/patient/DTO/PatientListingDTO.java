package br.rutiquewiski.HealthBridge.domain.patient.DTO;

import br.rutiquewiski.HealthBridge.domain.patient.Patient;

public record PatientListingDTO(
        String name,
        String email,
        String phone,
        String document
) {
    public PatientListingDTO(Patient patient) {
        this(patient.getName(), patient.getEmail(), patient.getPhone(), patient.getDocument());
    }
}
