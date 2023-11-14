package br.rutiquewiski.HealthBridge.repositories;

import br.rutiquewiski.HealthBridge.domain.appointment.dentist.Appointment_Dentist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Appointment_DentistRepository extends JpaRepository<Appointment_Dentist, Integer> {
}
