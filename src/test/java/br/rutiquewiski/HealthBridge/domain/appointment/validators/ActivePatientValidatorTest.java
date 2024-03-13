package br.rutiquewiski.HealthBridge.domain.appointment.validators;

import br.rutiquewiski.HealthBridge.domain.appointment.dentist.DTO.CreateDentistAppointmentDTO;
import br.rutiquewiski.HealthBridge.domain.appointment.doctor.DTO.CreateDoctorAppointmentDTO;
import br.rutiquewiski.HealthBridge.domain.patient.Patient;
import br.rutiquewiski.HealthBridge.infra.errors.ValidationException;
import br.rutiquewiski.HealthBridge.repositories.PatientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ActivePatientValidatorTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private ActivePatientValidator activePatientValidator;

    @Test
    public void testValidateDentistAppointmentValidPatient() {
        // Mock active patient
        Patient activePatient = new Patient();
        activePatient.setActive(true);
        when(patientRepository.getReferenceById(anyInt())).thenReturn(activePatient);

        // Create a valid dentist appointment DTO
        CreateDentistAppointmentDTO dto = new CreateDentistAppointmentDTO(1, 1, LocalDateTime.now().plusDays(1), "Some Specialty");

        // Validate appointment
        assertDoesNotThrow(() -> activePatientValidator.validateDentistAppointment(dto));
    }

    @Test
    public void testValidateDoctorAppointmentValidPatient() {
        // Mock active patient
        Patient activePatient = new Patient();
        activePatient.setActive(true);
        when(patientRepository.getReferenceById(anyInt())).thenReturn(activePatient);

        // Create a valid doctor appointment DTO
        CreateDoctorAppointmentDTO dto = new CreateDoctorAppointmentDTO(1, 1, LocalDateTime.now().plusDays(1), "Some Specialty");

        // Validate appointment
        assertDoesNotThrow(() -> activePatientValidator.validateDoctorAppointment(dto));
    }

    @Test
    public void testValidateDentistAppointmentInvalidPatient() {
        // Mock inactive patient
        Patient inactivePatient = new Patient();
        inactivePatient.setActive(false);
        when(patientRepository.getReferenceById(anyInt())).thenReturn(inactivePatient);

        // Create a valid dentist appointment DTO
        CreateDentistAppointmentDTO dto = new CreateDentistAppointmentDTO(1, 1, LocalDateTime.now().plusDays(1), "Some Specialty");

        // Validate appointment and ensure ValidationException is thrown
        assertThrows(ValidationException.class, () -> activePatientValidator.validateDentistAppointment(dto));
    }

    @Test
    public void testValidateDoctorAppointmentInvalidPatient() {
        // Mock inactive patient
        Patient inactivePatient = new Patient();
        inactivePatient.setActive(false);
        when(patientRepository.getReferenceById(anyInt())).thenReturn(inactivePatient);

        // Create a valid doctor appointment DTO
        CreateDoctorAppointmentDTO dto = new CreateDoctorAppointmentDTO(1, 1, LocalDateTime.now().plusDays(1), "Some Specialty");

        // Validate appointment and ensure ValidationException is thrown
        assertThrows(ValidationException.class, () -> activePatientValidator.validateDoctorAppointment(dto));
    }
}

