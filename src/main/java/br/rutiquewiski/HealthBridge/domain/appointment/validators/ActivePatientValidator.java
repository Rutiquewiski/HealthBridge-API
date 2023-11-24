package br.rutiquewiski.HealthBridge.domain.appointment.validators;

import br.rutiquewiski.HealthBridge.domain.appointment.dentist.DTO.CreateDentistAppointmentDTO;
import br.rutiquewiski.HealthBridge.domain.appointment.doctor.DTO.CreateDoctorAppointmentDTO;
import br.rutiquewiski.HealthBridge.domain.patient.Patient;
import br.rutiquewiski.HealthBridge.infra.errors.ValidationException;
import br.rutiquewiski.HealthBridge.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivePatientValidator implements AppointmentCreationValidator{

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public void validateDentistAppointment(CreateDentistAppointmentDTO createDentistAppointmentDTO) throws ValidationException {
        Patient patient = patientRepository.getReferenceById(createDentistAppointmentDTO.patientId());

        if (!patient.isActive()){
            throw new ValidationException("Invalid patient");
        }
    }

    @Override
    public void validateDoctorAppointment(CreateDoctorAppointmentDTO createDoctorAppointmentDTO) throws ValidationException {
        Patient patient = patientRepository.getReferenceById(createDoctorAppointmentDTO.patientId());

        if (!patient.isActive()){
            throw new ValidationException("Invalid Patient");
        }
    }
}
