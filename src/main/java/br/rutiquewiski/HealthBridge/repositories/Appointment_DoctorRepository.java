package br.rutiquewiski.HealthBridge.repositories;

import br.rutiquewiski.HealthBridge.domain.appointment.doctor.Appointment_Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Appointment_DoctorRepository extends JpaRepository<Appointment_Doctor, Integer> {
}
