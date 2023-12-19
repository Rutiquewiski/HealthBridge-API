package br.rutiquewiski.HealthBridge.repositories;

import br.rutiquewiski.HealthBridge.domain.appointment.doctor.Appointment_Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface Appointment_DoctorRepository extends JpaRepository<Appointment_Doctor, Integer> {

    boolean existsByDoctorIdAndDate(Integer doctorId, LocalDateTime date);

    boolean existsByPatientIdAndDateBetween(Integer patientId, LocalDateTime first, LocalDateTime last);
}
