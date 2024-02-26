package br.rutiquewiski.HealthBridge.repositories;

import br.rutiquewiski.HealthBridge.domain.professional.dentist.Dentist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface DentistRepository extends JpaRepository<Dentist, Integer> {

    Page<Dentist> findAllByActiveTrue(Pageable pageable);

    Page<Dentist> findAllByActiveTrueAndNameContaining(Pageable pageable, String name);

    Dentist findByIdAndActiveTrue(Integer id);

    @Query("SELECT d FROM Dentist d JOIN d.specialties ds WHERE ds.name = :specialty AND d.active = true AND d.id NOT IN (SELECT ad.dentist.id FROM Appointment_Dentist ad WHERE ad.date = :date) ORDER BY FUNCTION('RAND') LIMIT 1")
    Dentist findRandomDentist(String specialty, LocalDateTime date);
}
