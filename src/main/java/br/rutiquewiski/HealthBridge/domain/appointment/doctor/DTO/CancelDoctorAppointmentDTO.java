package br.rutiquewiski.HealthBridge.domain.appointment.doctor.DTO;

import jakarta.validation.constraints.NotNull;

public record CancelDoctorAppointmentDTO(
        @NotNull Integer appointmentId
) {
}
