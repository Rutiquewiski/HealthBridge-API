package br.rutiquewiski.HealthBridge.domain.professional.doctor.DTO;

import br.rutiquewiski.HealthBridge.domain.professional.doctor.Doctor;
import br.rutiquewiski.HealthBridge.domain.professional.doctor.MedicalSpecialty;

import java.util.List;

public record DoctorListingDTO(
        int id,
        String name,
        String email,
        String phone,
        String document,
        List<MedicalSpecialty> specialties
) {

    public DoctorListingDTO(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getPhone(), doctor.getDocument(), doctor.getSpecialties());
    }
}
