package br.rutiquewiski.HealthBridge.domain.appointment.dentist.DTO;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CreateDentistAppointmentDTO(
        Integer dentistId,
        @NotNull Integer patientId,
        @NotNull @Future LocalDateTime date,
        String dentalSpecialty
) {}
