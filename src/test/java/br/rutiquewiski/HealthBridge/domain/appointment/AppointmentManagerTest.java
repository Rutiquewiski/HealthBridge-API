package br.rutiquewiski.HealthBridge.domain.appointment;

import br.rutiquewiski.HealthBridge.domain.appointment.dentist.Appointment_Dentist;
import br.rutiquewiski.HealthBridge.domain.appointment.dentist.DTO.CreateDentistAppointmentDTO;
import br.rutiquewiski.HealthBridge.domain.appointment.doctor.Appointment_Doctor;
import br.rutiquewiski.HealthBridge.domain.appointment.doctor.DTO.CreateDoctorAppointmentDTO;
import br.rutiquewiski.HealthBridge.domain.appointment.validators.AppointmentCreationValidator;
import br.rutiquewiski.HealthBridge.domain.patient.Patient;
import br.rutiquewiski.HealthBridge.domain.professional.dentist.Dentist;
import br.rutiquewiski.HealthBridge.domain.professional.doctor.Doctor;
import br.rutiquewiski.HealthBridge.infra.errors.ValidationException;
import br.rutiquewiski.HealthBridge.repositories.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AppointmentManagerTest {

    @Mock
    private DentistRepository dentistRepository;

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private AppointmentDentistRepository appointmentDentistRepository;

    @Mock
    private AppointmentDoctorRepository appointmentDoctorRepository;

    @Mock
    private List<AppointmentCreationValidator> validators;

    @InjectMocks
    private AppointmentManager appointmentManager;

    @Test
    public void testCreateDentistAppointment_Valid() {
        // Mock patient existence and retrieval
        when(patientRepository.existsById(anyInt())).thenReturn(true);
        when(patientRepository.findByIdAndActiveTrue(anyInt())).thenReturn(new Patient());

        // Mock validator to pass
        doNothing().when(validators).forEach(any());

        // Mock dentist existence check
        when(dentistRepository.existsById(anyInt())).thenReturn(true);

        // Mock dentist retrieval
        when(dentistRepository.findByIdAndActiveTrue(anyInt())).thenReturn(new Dentist());

        // Mock appointment save
        when(appointmentDentistRepository.save(any())).thenReturn(new Appointment_Dentist());

        // Valid DTO
        CreateDentistAppointmentDTO validDTO = new CreateDentistAppointmentDTO(1, 1, LocalDateTime.now().plusDays(1), "someDentalSpecialty");

        // Test appointment creation
        assertDoesNotThrow(() -> appointmentManager.createDentistAppointment(validDTO));
    }

    @Test
    public void testCreateDentistAppointment_InvalidPatient() {
        // Mock patient existence check
        when(patientRepository.existsById(anyInt())).thenReturn(false);

        // Invalid DTO
        CreateDentistAppointmentDTO invalidDTO = new CreateDentistAppointmentDTO(1, 1, LocalDateTime.now().plusDays(1), "someDentalSpecialty");

        // Test appointment creation with invalid patient
        assertThrows(ValidationException.class, () -> appointmentManager.createDentistAppointment(invalidDTO));
    }

    @Test
    public void testCreateDentistAppointment_InvalidDentist() {
        // Mock patient existence and retrieval
        when(patientRepository.existsById(anyInt())).thenReturn(true);
        when(patientRepository.findByIdAndActiveTrue(anyInt())).thenReturn(new Patient());

        // Mock dentist existence check
        when(dentistRepository.existsById(anyInt())).thenReturn(false);

        // Invalid DTO
        CreateDentistAppointmentDTO invalidDTO = new CreateDentistAppointmentDTO(1, 1, LocalDateTime.now().plusDays(1), "someDentalSpecialty");

        // Test appointment creation with invalid dentist
        assertThrows(ValidationException.class, () -> appointmentManager.createDentistAppointment(invalidDTO));
    }

    // Add more tests for createDentistAppointment with other scenarios

    @Test
    public void testCancelDentistAppointment() {
        // Mock appointment retrieval
        when(appointmentDentistRepository.findById(anyInt())).thenReturn(Optional.of(new Appointment_Dentist()));

        // Test cancellation
        assertDoesNotThrow(() -> appointmentManager.cancelDentistAppointment(1));
    }

    @Test
    public void testCreateDoctorAppointment_Valid() {
        // Mock patient existence and retrieval
        when(patientRepository.existsById(anyInt())).thenReturn(true);
        when(patientRepository.findByIdAndActiveTrue(anyInt())).thenReturn(new Patient());

        // Mock validator to pass
        doNothing().when(validators).forEach(any());

        // Mock doctor existence check
        when(doctorRepository.existsById(anyInt())).thenReturn(true);

        // Mock doctor retrieval
        when(doctorRepository.findByIdAndActiveTrue(anyInt())).thenReturn(new Doctor());

        // Mock appointment save
        when(appointmentDoctorRepository.save(any())).thenReturn(new Appointment_Doctor());

        // Valid DTO
        CreateDoctorAppointmentDTO validDTO = new CreateDoctorAppointmentDTO(1, 1, LocalDateTime.now().plusDays(1), "someMedicalSpecialty");

        // Test appointment creation
        assertDoesNotThrow(() -> appointmentManager.createDoctorAppointment(validDTO));
    }

    @Test
    public void testCreateDoctorAppointment_InvalidPatient() {
        // Mock patient existence check
        when(patientRepository.existsById(anyInt())).thenReturn(false);

        // Invalid DTO
        CreateDoctorAppointmentDTO invalidDTO = new CreateDoctorAppointmentDTO(1, 1, LocalDateTime.now().plusDays(1), "someMedicalSpecialty");

        // Test appointment creation with invalid patient
        assertThrows(ValidationException.class, () -> appointmentManager.createDoctorAppointment(invalidDTO));
    }

    @Test
    public void testCreateDoctorAppointment_InvalidDoctor() {
        // Mock patient existence and retrieval
        when(patientRepository.existsById(anyInt())).thenReturn(true);
        when(patientRepository.findByIdAndActiveTrue(anyInt())).thenReturn(new Patient());

        // Mock doctor existence check
        when(doctorRepository.existsById(anyInt())).thenReturn(false);

        // Invalid DTO
        CreateDoctorAppointmentDTO invalidDTO = new CreateDoctorAppointmentDTO(1, 1, LocalDateTime.now().plusDays(1), "someMedicalSpecialty");

        // Test appointment creation with invalid doctor
        assertThrows(ValidationException.class, () -> appointmentManager.createDoctorAppointment(invalidDTO));
    }

    @Test
    public void testCancelDoctorAppointment() {
        // Mock appointment retrieval
        when(appointmentDoctorRepository.findById(anyInt())).thenReturn(Optional.of(new Appointment_Doctor()));

        // Test cancellation
        assertDoesNotThrow(() -> appointmentManager.cancelDoctorAppointment(1));
    }


}

