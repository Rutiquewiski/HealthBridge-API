package br.rutiquewiski.HealthBridge.domain.appointment.doctor.DTO;

import br.rutiquewiski.HealthBridge.domain.appointment.doctor.Appointment_Doctor;

import java.time.LocalDateTime;

public record ListDoctorAppointmentDTO(
        Integer appointmentId,
        Integer doctorId,
        Integer patientId,
        LocalDateTime date
) {

    public ListDoctorAppointmentDTO(Appointment_Doctor appointment_doctor) {
        this(
                appointment_doctor.getId(),
                appointment_doctor.getDoctor().getId(),
                appointment_doctor.getPatient().getId(),
                appointment_doctor.getDate()
        );
    }
}
