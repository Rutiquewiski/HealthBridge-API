package br.rutiquewiski.HealthBridge.domain.appointment.dentist.DTO;

import br.rutiquewiski.HealthBridge.domain.appointment.dentist.Appointment_Dentist;

import java.time.LocalDateTime;

public record ListDentistAppointmentDTO(
        Integer appointmentId,
        Integer dentistId,
        Integer patientId,
        LocalDateTime date
) {

    public ListDentistAppointmentDTO(Appointment_Dentist appointment_dentist) {
        this(
                appointment_dentist.getId(),
                appointment_dentist.getDentist().getId(),
                appointment_dentist.getPatient().getId(),
                appointment_dentist.getDate()
        );
    }
}
