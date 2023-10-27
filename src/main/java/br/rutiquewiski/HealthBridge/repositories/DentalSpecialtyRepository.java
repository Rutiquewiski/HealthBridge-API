package br.rutiquewiski.HealthBridge.repositories;

import br.rutiquewiski.HealthBridge.domain.professional.Dentist.DentalSpecialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DentalSpecialtyRepository extends JpaRepository<DentalSpecialty, String> {

    DentalSpecialty findByName(String name);

}
