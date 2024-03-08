package br.rutiquewiski.HealthBridge.domain.appointment.validators;

import br.rutiquewiski.HealthBridge.domain.appointment.dentist.DTO.CreateDentistAppointmentDTO;
import br.rutiquewiski.HealthBridge.domain.appointment.doctor.DTO.CreateDoctorAppointmentDTO;
import br.rutiquewiski.HealthBridge.infra.errors.ValidationException;
import br.rutiquewiski.HealthBridge.repositories.AppointmentDentistRepository;
import br.rutiquewiski.HealthBridge.repositories.AppointmentDoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DuplicateValidator implements AppointmentCreationValidator{

    //This validator checks if the patient already has an appointment on this date

    @Autowired
    private AppointmentDentistRepository appointment_dentistRepository;

    @Autowired
    private AppointmentDoctorRepository appointment_doctorRepository;

    @Override
    public void validateDentistAppointment(CreateDentistAppointmentDTO createDentistAppointmentDTO) throws ValidationException {

        var firstTime = createDentistAppointmentDTO.date().withHour(7);
        var lastTime = createDentistAppointmentDTO.date().withHour(18);
        var appointmentExists = appointment_dentistRepository.existsByPatientIdAndDateBetween(createDentistAppointmentDTO.patientId(), firstTime, lastTime);

        if (appointmentExists) {
            throw new ValidationException("This patient already has an appointment on this date");
        }

    }

    @Override
    public void validateDoctorAppointment(CreateDoctorAppointmentDTO createDoctorAppointmentDTO) throws ValidationException {

        var firstTime = createDoctorAppointmentDTO.date().withHour(7);
        var lastTime = createDoctorAppointmentDTO.date().withHour(18);
        var appointmentExists = appointment_doctorRepository.existsByPatientIdAndDateBetween(createDoctorAppointmentDTO.patientId(), firstTime, lastTime);

        if (appointmentExists) {
            throw new ValidationException("This patient already has an appointment on this date");
        }
    }
}
