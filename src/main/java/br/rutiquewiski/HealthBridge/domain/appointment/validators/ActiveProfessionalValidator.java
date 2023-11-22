package br.rutiquewiski.HealthBridge.domain.appointment.validators;

import br.rutiquewiski.HealthBridge.domain.appointment.dentist.DTO.CreateDentistAppointmentDTO;
import br.rutiquewiski.HealthBridge.domain.appointment.doctor.DTO.CreateDoctorAppointmentDTO;
import br.rutiquewiski.HealthBridge.domain.professional.dentist.Dentist;
import br.rutiquewiski.HealthBridge.domain.professional.doctor.Doctor;
import br.rutiquewiski.HealthBridge.infra.errors.ValidationException;
import br.rutiquewiski.HealthBridge.repositories.DentistRepository;
import br.rutiquewiski.HealthBridge.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ActiveProfessionalValidator implements AppointmentCreationValidator{

    @Autowired
    private DentistRepository dentistRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public void validateDentistAppointment(CreateDentistAppointmentDTO createDentistAppointmentDTO) throws ValidationException {

        if (createDentistAppointmentDTO.dentistId() == null){
            return;
        }

        Dentist dentist = dentistRepository.getReferenceById(createDentistAppointmentDTO.dentistId());

        if (!dentist.isActive()){
            throw new ValidationException("Invalid dentist");
        }
    }

    @Override
    public void validateDoctorAppointment(CreateDoctorAppointmentDTO createDoctorAppointmentDTO) throws ValidationException {

        if (createDoctorAppointmentDTO.doctorId() == null){
            return;
        }

        Doctor doctor = doctorRepository.getReferenceById(createDoctorAppointmentDTO.doctorId());

        if (!doctor.isActive()){
            throw new ValidationException("Invalid doctor");
        }
    }
}
