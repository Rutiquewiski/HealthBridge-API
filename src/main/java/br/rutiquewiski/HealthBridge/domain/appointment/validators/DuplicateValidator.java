package br.rutiquewiski.HealthBridge.domain.appointment.validators;

import br.rutiquewiski.HealthBridge.domain.appointment.dentist.DTO.CreateDentistAppointmentDTO;
import br.rutiquewiski.HealthBridge.domain.appointment.doctor.DTO.CreateDoctorAppointmentDTO;
import br.rutiquewiski.HealthBridge.infra.errors.ValidationException;
import br.rutiquewiski.HealthBridge.repositories.Appointment_DentistRepository;
import br.rutiquewiski.HealthBridge.repositories.Appointment_DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DuplicateValidator implements AppointmentCreationValidator{

    //This validator checks if the patient already has an appointment on this date

    @Autowired
    private Appointment_DentistRepository appointment_dentistRepository;

    @Autowired
    private Appointment_DoctorRepository appointment_doctorRepository;

    @Override
    public void validateDentistAppointment(CreateDentistAppointmentDTO createDentistAppointmentDTO) throws ValidationException {

        var firstTime = createDentistAppointmentDTO.date().withHour(7);
        var lastTime = createDentistAppointmentDTO.date().withHour(18);
        var appointmentExists = appointment_dentistRepository.existsByPatientIdAndCanceledFalseAndDateBetween(createDentistAppointmentDTO.patientId(), firstTime, lastTime);

        if (appointmentExists) {
            throw new ValidationException("This patient already has an appointment on this date");
        }

    }

    @Override
    public void validateDoctorAppointment(CreateDoctorAppointmentDTO createDoctorAppointmentDTO) throws ValidationException {

        var firstTime = createDoctorAppointmentDTO.date().withHour(7);
        var lastTime = createDoctorAppointmentDTO.date().withHour(18);
        var appointmentExists = appointment_doctorRepository.existsByPatientIdAndCanceledFalseAndDateBetween(createDoctorAppointmentDTO.patientId(), firstTime, lastTime);

        if (appointmentExists) {
            throw new ValidationException("This patient already has an appointment on this date");
        }
    }
}
