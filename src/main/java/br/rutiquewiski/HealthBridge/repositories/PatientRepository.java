package br.rutiquewiski.HealthBridge.repositories;

import br.rutiquewiski.HealthBridge.domain.patient.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    Page<Patient> findAllByActiveTrue(Pageable pageable);

    Page<Patient> findAllByActiveTrueAndNameContaining(Pageable pageable, String name);

    Patient findByIdAndActiveTrue(Integer id);
}
