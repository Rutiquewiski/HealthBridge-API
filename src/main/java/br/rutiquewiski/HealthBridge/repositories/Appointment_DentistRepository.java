package br.rutiquewiski.HealthBridge.repositories;

import br.rutiquewiski.HealthBridge.domain.appointment.dentist.Appointment_Dentist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface Appointment_DentistRepository extends JpaRepository<Appointment_Dentist, Integer> {

    Page<Appointment_Dentist> findAllByCanceledFalse(Pageable paginacao);
}
