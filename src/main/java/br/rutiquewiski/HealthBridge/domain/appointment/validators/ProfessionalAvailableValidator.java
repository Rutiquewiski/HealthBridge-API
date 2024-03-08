package br.rutiquewiski.HealthBridge.domain.appointment.validators;

import br.rutiquewiski.HealthBridge.domain.appointment.dentist.DTO.CreateDentistAppointmentDTO;
import br.rutiquewiski.HealthBridge.domain.appointment.doctor.DTO.CreateDoctorAppointmentDTO;
import br.rutiquewiski.HealthBridge.infra.errors.ValidationException;
import br.rutiquewiski.HealthBridge.repositories.AppointmentDentistRepository;
import br.rutiquewiski.HealthBridge.repositories.AppointmentDoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfessionalAvailableValidator implements AppointmentCreationValidator{

    @Autowired
    private AppointmentDentistRepository appointment_dentistRepository;
    @Autowired
    private AppointmentDoctorRepository appointment_doctorRepository;

    @Override
    public void validateDentistAppointment(CreateDentistAppointmentDTO createDentistAppointmentDTO) throws ValidationException {

        boolean existsAppointment = appointment_dentistRepository.existsByDentistIdAndDate(createDentistAppointmentDTO.dentistId(), createDentistAppointmentDTO.date());

        if (existsAppointment){
            throw new ValidationException("Dentist unavailable");
        }
    }

    @Override
    public void validateDoctorAppointment(CreateDoctorAppointmentDTO createDoctorAppointmentDTO) throws ValidationException {

        boolean existsAppointment = appointment_doctorRepository.existsByDoctorIdAndDate(createDoctorAppointmentDTO.doctorId(), createDoctorAppointmentDTO.date());

        if (existsAppointment){
            throw new ValidationException("Doctor unavailable");
        }

    }
}
