package br.rutiquewiski.HealthBridge.repositories;

import br.rutiquewiski.HealthBridge.domain.professional.doctor.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    Page<Doctor> findAllByActiveTrue(Pageable pageable);

    Page<Doctor> findAllByActiveTrueAndNameContaining(Pageable pageable, String name);

    Doctor findByIdAndActiveTrue(Integer id);

    @Query("SELECT d FROM Doctor d JOIN d.specialties ds WHERE ds.name = :specialty AND d.active = true AND d.id NOT IN (SELECT ad.doctor.id FROM Appointment_Doctor ad WHERE ad.date = :date) ORDER BY FUNCTION('RAND') LIMIT 1")
    Doctor findRandomDoctor(String specialty, LocalDateTime date);
}
