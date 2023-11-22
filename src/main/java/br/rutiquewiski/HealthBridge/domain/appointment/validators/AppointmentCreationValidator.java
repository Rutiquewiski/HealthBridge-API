package br.rutiquewiski.HealthBridge.domain.appointment.validators;

import br.rutiquewiski.HealthBridge.domain.appointment.dentist.DTO.CreateDentistAppointmentDTO;
import br.rutiquewiski.HealthBridge.domain.appointment.doctor.DTO.CreateDoctorAppointmentDTO;
import br.rutiquewiski.HealthBridge.infra.errors.ValidationException;

public interface AppointmentCreationValidator {

    void validateDentistAppointment(CreateDentistAppointmentDTO createDentistAppointmentDTO) throws ValidationException;

    void validateDoctorAppointment(CreateDoctorAppointmentDTO createDoctorAppointmentDTO) throws ValidationException;
}
