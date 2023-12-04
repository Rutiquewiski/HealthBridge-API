package br.rutiquewiski.HealthBridge.repositories;

import br.rutiquewiski.HealthBridge.domain.appointment.dentist.Appointment_Dentist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface Appointment_DentistRepository extends JpaRepository<Appointment_Dentist, Integer> {

    Page<Appointment_Dentist> findAllByCanceledFalse(Pageable pageable);

    boolean existsByDentistIdAndDateAndCanceledFalse(Integer dentistId, LocalDateTime date);

    boolean existsByPatientIdAndCanceledFalseAndDateBetween(Integer patientId, LocalDateTime first, LocalDateTime last);
}
