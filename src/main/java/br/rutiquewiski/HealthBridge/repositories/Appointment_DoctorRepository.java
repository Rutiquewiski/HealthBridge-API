package br.rutiquewiski.HealthBridge.repositories;

import br.rutiquewiski.HealthBridge.domain.appointment.doctor.Appointment_Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface Appointment_DoctorRepository extends JpaRepository<Appointment_Doctor, Integer> {

    Page<Appointment_Doctor> findAllByCanceledFalse(Pageable paginacao);

    boolean existsByDoctorIdAndDateAndCanceledFalse(Integer doctorId, LocalDateTime date);

    boolean existsByPatientIdAndCanceledFalseAndDateBetween(Integer patientId, LocalDateTime first, LocalDateTime last);
}
