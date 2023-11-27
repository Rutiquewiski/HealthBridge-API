package br.rutiquewiski.HealthBridge.repositories;

import br.rutiquewiski.HealthBridge.domain.professional.doctor.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    Page<Doctor> findAllByActiveTrue(Pageable pageable);

    Page<Doctor> findAllByActiveTrueAndNameContaining(Pageable pageable, String name);

    Doctor findByIdAndActiveTrue(Integer id);
}
