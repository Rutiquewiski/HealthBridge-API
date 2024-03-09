package br.rutiquewiski.HealthBridge.repositories;

import br.rutiquewiski.HealthBridge.domain.appointment.dentist.Appointment_Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AppointmentDentistRepository extends JpaRepository<Appointment_Dentist, Integer> {

    boolean existsByDentistIdAndDate(Integer dentistId, LocalDateTime date);

    boolean existsByPatientIdAndDateBetween(Integer patientId, LocalDateTime first, LocalDateTime last);
}
