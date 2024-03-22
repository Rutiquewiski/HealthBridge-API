package br.rutiquewiski.HealthBridge.domain.appointment.doctor.DTO;

import br.rutiquewiski.HealthBridge.domain.appointment.doctor.Appointment_Doctor;
import br.rutiquewiski.HealthBridge.domain.patient.Patient;
import br.rutiquewiski.HealthBridge.domain.professional.doctor.Doctor;

import java.time.LocalDateTime;

public record DoctorAppointmentListingDTO(int id, Doctor doctor, Patient patient, LocalDateTime date) {

    public DoctorAppointmentListingDTO(Appointment_Doctor appointment_doctor) {
        this(appointment_doctor.getId(), appointment_doctor.getDoctor(), appointment_doctor.getPatient(), appointment_doctor.getDate());
    }
}
