package br.rutiquewiski.HealthBridge.domain.appointment.dentist.DTO;

import br.rutiquewiski.HealthBridge.domain.appointment.dentist.Appointment_Dentist;
import br.rutiquewiski.HealthBridge.domain.patient.Patient;
import br.rutiquewiski.HealthBridge.domain.professional.dentist.Dentist;

import java.time.LocalDateTime;

public record DentistAppointmentListingDTO(int id, Dentist dentist, Patient patient, LocalDateTime date) {

    public DentistAppointmentListingDTO(Appointment_Dentist appointment_dentist) {
        this(appointment_dentist.getId(), appointment_dentist.getDentist(), appointment_dentist.getPatient(), appointment_dentist.getDate());
    }
}
