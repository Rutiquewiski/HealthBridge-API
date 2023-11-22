package br.rutiquewiski.HealthBridge.domain.appointment.dentist.DTO;

import jakarta.validation.constraints.NotNull;

public record CancelDentistAppointmentDTO(
        @NotNull Integer appointmentId
) {}
