package br.rutiquewiski.HealthBridge.domain.appointment.doctor.DTO;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateDoctorAppointmentDTO(
        Integer doctorId,
        @NotNull Integer patientId,
        @NotNull @Future LocalDateTime date,
        String medicalSpecialty
) {
}
