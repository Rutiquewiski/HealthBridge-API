package br.rutiquewiski.HealthBridge.repositories;

import br.rutiquewiski.HealthBridge.domain.professional.Doctor.MedicalSpecialty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalSpecialtyRepository extends JpaRepository<MedicalSpecialty, String> {

    MedicalSpecialty findByName(String name);
}
