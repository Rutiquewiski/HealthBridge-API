package br.rutiquewiski.HealthBridge.domain.professional.doctor.DTO;

import br.rutiquewiski.HealthBridge.domain.adress.Adress;
import br.rutiquewiski.HealthBridge.domain.professional.doctor.Doctor;
import br.rutiquewiski.HealthBridge.domain.professional.doctor.MedicalSpecialty;

import java.util.List;

public record DoctorDetailsDTO(
        Integer id,
        String name,
        String email,
        String phone,
        String document,
        Adress adress,
        List<MedicalSpecialty> specialties
) {

    public DoctorDetailsDTO(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getPhone(), doctor.getDocument(), doctor.getAdress(), doctor.getSpecialties());
    }
}
