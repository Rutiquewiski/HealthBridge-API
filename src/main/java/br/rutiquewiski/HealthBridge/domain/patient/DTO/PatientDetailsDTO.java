package br.rutiquewiski.HealthBridge.domain.patient.DTO;

import br.rutiquewiski.HealthBridge.domain.adress.Address;
import br.rutiquewiski.HealthBridge.domain.patient.Patient;

public record PatientDetailsDTO(
        Integer id,
        String name,
        String email,
        String phone,
        String document,
        Address address,
        String medicalHistory
) {
    public PatientDetailsDTO(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getPhone(), patient.getDocument(), patient.getAddress(), patient.getMedicalHistory());
    }
}
