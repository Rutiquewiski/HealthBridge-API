package br.rutiquewiski.HealthBridge.domain.appointment.validators;

import br.rutiquewiski.HealthBridge.domain.appointment.dentist.DTO.CreateDentistAppointmentDTO;
import br.rutiquewiski.HealthBridge.domain.appointment.doctor.DTO.CreateDoctorAppointmentDTO;
import br.rutiquewiski.HealthBridge.infra.errors.ValidationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AdvanceValidator implements AppointmentCreationValidator{
    @Override
    public void validateDentistAppointment(CreateDentistAppointmentDTO createDentistAppointmentDTO) throws ValidationException {

        var appointmentDate = createDentistAppointmentDTO.date();
        var now = LocalDateTime.now();
        var diff = Duration.between(now, appointmentDate).toMinutes();

        if (diff < 30) {
            throw new ValidationException("Appointment must be at least 30 minutes in advance");
        }
    }

    @Override
    public void validateDoctorAppointment(CreateDoctorAppointmentDTO createDoctorAppointmentDTO) throws ValidationException {

        var appointmentDate = createDoctorAppointmentDTO.date();
        var now = LocalDateTime.now();
        var diff = Duration.between(now, appointmentDate).toMinutes();

        if (diff < 30) {
            throw new ValidationException("Appointment must be at least 30 minutes in advance");
        }
    }
}
