package br.rutiquewiski.HealthBridge.domain.appointment;

import br.rutiquewiski.HealthBridge.domain.appointment.dentist.Appointment_Dentist;
import br.rutiquewiski.HealthBridge.domain.appointment.dentist.DTO.CreateDentistAppointmentDTO;
import br.rutiquewiski.HealthBridge.domain.appointment.dentist.DTO.ListDentistAppointmentDTO;
import br.rutiquewiski.HealthBridge.domain.appointment.doctor.Appointment_Doctor;
import br.rutiquewiski.HealthBridge.domain.appointment.doctor.DTO.CreateDoctorAppointmentDTO;
import br.rutiquewiski.HealthBridge.domain.appointment.doctor.DTO.ListDoctorAppointmentDTO;
import br.rutiquewiski.HealthBridge.domain.appointment.validators.AppointmentCreationValidator;
import br.rutiquewiski.HealthBridge.domain.patient.Patient;
import br.rutiquewiski.HealthBridge.domain.professional.dentist.Dentist;
import br.rutiquewiski.HealthBridge.domain.professional.doctor.Doctor;
import br.rutiquewiski.HealthBridge.infra.errors.ValidationException;
import br.rutiquewiski.HealthBridge.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentManager {

    @Autowired
    private DentistRepository dentistRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private Appointment_DentistRepository appointment_dentistRepository;

    @Autowired
    private Appointment_DoctorRepository appointment_doctorRepository;

    @Autowired
    private List<AppointmentCreationValidator> validators;  //Spring automatically detects all classes that use the interface between <> and adds them to the list



    public ListDentistAppointmentDTO createDentistAppointment(CreateDentistAppointmentDTO createDentistAppointmentDTO) throws ValidationException {

        if (!patientRepository.existsById(createDentistAppointmentDTO.patientId())) {

            throw new ValidationException("Invalid Patient!");

        }

        Patient patient = patientRepository.findByIdAndActiveTrue(createDentistAppointmentDTO.patientId());


        if (createDentistAppointmentDTO.dentistId() != null && !dentistRepository.existsById(createDentistAppointmentDTO.dentistId())) {

            throw new ValidationException("Invalid Dentist!");

        }

        validators.forEach(v -> {
            try {
                v.validateDentistAppointment(createDentistAppointmentDTO);
            } catch (ValidationException e) {
                throw new RuntimeException(e);
            }
        });

        Dentist dentist = selectDentist(createDentistAppointmentDTO);

        if (dentist == null) {
            throw new ValidationException("No dentists available for this date");
        }

        Appointment_Dentist appointment_dentist = new Appointment_Dentist(null, dentist, patient, createDentistAppointmentDTO.date(), false);
        appointment_dentistRepository.save(appointment_dentist);
        return new ListDentistAppointmentDTO(appointment_dentist);
    }

    public ListDoctorAppointmentDTO createDoctorAppointment(CreateDoctorAppointmentDTO createDoctorAppointmentDTO) throws ValidationException {

        if (!patientRepository.existsById(createDoctorAppointmentDTO.patientId())) {

            throw new ValidationException("Invalid Patient!");

        }

        Patient patient = patientRepository.findByIdAndActiveTrue(createDoctorAppointmentDTO.patientId());


        if (createDoctorAppointmentDTO.doctorId() != null && !doctorRepository.existsById(createDoctorAppointmentDTO.doctorId())) {

            throw new ValidationException("Invalid Doctor!");

        }

        validators.forEach(v -> {
            try {
                v.validateDoctorAppointment(createDoctorAppointmentDTO);
            } catch (ValidationException e) {
                throw new RuntimeException(e);
            }
        });

        Doctor doctor = selectDoctor(createDoctorAppointmentDTO);

        if (doctor == null) {
            throw new ValidationException("No doctors available for this date");
        }

        Appointment_Doctor appointment_doctor = new Appointment_Doctor(null, doctor, patient, createDoctorAppointmentDTO.date(), false);
        appointment_doctorRepository.save(appointment_doctor);
        return new ListDoctorAppointmentDTO(appointment_doctor);
    }

    private Dentist selectDentist(CreateDentistAppointmentDTO createDentistAppointmentDTO) throws ValidationException {

        if (createDentistAppointmentDTO.dentistId() != null) {
            return dentistRepository.findByIdAndActiveTrue(createDentistAppointmentDTO.dentistId());
        }

        if (createDentistAppointmentDTO.dentalSpecialty() == null) {
            throw new ValidationException("Specialty is required when dentist not selected");
        }

        return dentistRepository.findRandomDentist(createDentistAppointmentDTO.dentalSpecialty(), createDentistAppointmentDTO.date());
    }

    private Doctor selectDoctor(CreateDoctorAppointmentDTO createDoctorAppointmentDTO) throws ValidationException {

        if (createDoctorAppointmentDTO.doctorId() != null) {
            return doctorRepository.findByIdAndActiveTrue(createDoctorAppointmentDTO.doctorId());
        }

        if (createDoctorAppointmentDTO.medicalSpecialty() == null) {
            throw new ValidationException("Specialty is required when doctor not selected");
        }

        return doctorRepository.findRandomDoctor(createDoctorAppointmentDTO.medicalSpecialty(), createDoctorAppointmentDTO.date());
    }
}
