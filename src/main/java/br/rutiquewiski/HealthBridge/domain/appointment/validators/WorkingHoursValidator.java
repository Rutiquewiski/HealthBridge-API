package br.rutiquewiski.HealthBridge.domain.appointment.validators;

import br.rutiquewiski.HealthBridge.domain.appointment.dentist.DTO.CreateDentistAppointmentDTO;
import br.rutiquewiski.HealthBridge.domain.appointment.doctor.DTO.CreateDoctorAppointmentDTO;
import br.rutiquewiski.HealthBridge.infra.errors.ValidationException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class WorkingHoursValidator implements AppointmentCreationValidator{

    @Override
    public void validateDentistAppointment(CreateDentistAppointmentDTO createDentistAppointmentDTO) throws ValidationException {

        var appointmentDate = createDentistAppointmentDTO.date();
        var sunday = appointmentDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var closedMorning = appointmentDate.getHour() < 7;
        var closedNight = appointmentDate.getHour() > 18;

        if (sunday || closedMorning || closedNight) {
            throw new ValidationException("Appointment not in working hours");
        }

    }

    @Override
    public void validateDoctorAppointment(CreateDoctorAppointmentDTO createDoctorAppointmentDTO) throws ValidationException {

        var appointmentDate = createDoctorAppointmentDTO.date();
        var sunday = appointmentDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var closedMorning = appointmentDate.getHour() < 7;
        var closedNight = appointmentDate.getHour() > 18;

        if (sunday || closedMorning || closedNight) {
            throw new ValidationException("Appointment not in working hours");
        }

    }
}
