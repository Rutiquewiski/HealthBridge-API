package br.rutiquewiski.HealthBridge.domain.professional.Doctor.DTO;

import br.rutiquewiski.HealthBridge.domain.professional.Doctor.Doctor;
import br.rutiquewiski.HealthBridge.domain.professional.Doctor.MedicalSpecialty;

import java.util.List;

public record DoctorListingDTO(
        String name,
        String email,
        String phone,
        String document,
        List<MedicalSpecialty> specialties
) {

    public DoctorListingDTO(Doctor doctor) {
        this(doctor.getName(), doctor.getEmail(), doctor.getPhone(), doctor.getDocument(), doctor.getSpecialties());
    }
}
