package br.rutiquewiski.HealthBridge.repositories;

import br.rutiquewiski.HealthBridge.domain.professional.doctor.MedicalSpecialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalSpecialtyRepository extends JpaRepository<MedicalSpecialty, String> {

    MedicalSpecialty findByName(String name);
}
