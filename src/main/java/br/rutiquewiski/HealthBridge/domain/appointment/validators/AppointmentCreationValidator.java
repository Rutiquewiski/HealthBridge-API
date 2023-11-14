package br.rutiquewiski.HealthBridge.domain.appointment.validators;

import br.rutiquewiski.HealthBridge.domain.appointment.dentist.DTO.CreateDentistAppointmentDTO;
import br.rutiquewiski.HealthBridge.domain.appointment.doctor.DTO.CreateDoctorAppointmentDTO;
import br.rutiquewiski.HealthBridge.infra.errors.ValidationException;

public interface AppointmentCreationValidator {

    void validate(CreateDentistAppointmentDTO createDentistAppointmentDTO) throws ValidationException;

    void validate(CreateDoctorAppointmentDTO createDoctorAppointmentDTO) throws ValidationException;
}
