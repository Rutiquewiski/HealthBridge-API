package br.rutiquewiski.HealthBridge.domain.appointment.validators;

import br.rutiquewiski.HealthBridge.domain.appointment.dentist.DTO.CreateDentistAppointmentDTO;
import br.rutiquewiski.HealthBridge.domain.appointment.doctor.DTO.CreateDoctorAppointmentDTO;
import br.rutiquewiski.HealthBridge.infra.errors.ValidationException;
import br.rutiquewiski.HealthBridge.repositories.AppointmentDentistRepository;
import br.rutiquewiski.HealthBridge.repositories.AppointmentDoctorRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DuplicateValidatorTest {

    @Mock
    private AppointmentDentistRepository mockDentistRepository;

    @Mock
    private AppointmentDoctorRepository mockDoctorRepository;

    @InjectMocks
    private DuplicateValidator duplicateValidator;

    @Test
    public void testValidateDentistAppointment_NoExistingAppointment() {
        // Mock the repository to return false (no existing appointment)
        when(mockDentistRepository.existsByPatientIdAndDateBetween(anyInt(), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(false);

        // Create a valid CreateDentistAppointmentDTO
        CreateDentistAppointmentDTO validDTO = new CreateDentistAppointmentDTO(1, 1, LocalDateTime.now().plusDays(1), "someDentalSpecialty");

        // Validation should pass without throwing exception
        assertDoesNotThrow(() -> duplicateValidator.validateDentistAppointment(validDTO));

        // Verify repository method was called
        verify(mockDentistRepository).existsByPatientIdAndDateBetween(1, validDTO.date().withHour(7), validDTO.date().withHour(18));
    }

    @Test
    public void testValidateDentistAppointment_ExistingAppointment() {
        // Mock the repository to return true (existing appointment)
        when(mockDentistRepository.existsByPatientIdAndDateBetween(anyInt(), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(true);

        // Create an invalid CreateDentistAppointmentDTO
        CreateDentistAppointmentDTO invalidDTO = new CreateDentistAppointmentDTO(1, 1, LocalDateTime.now().plusDays(1), "someDentalSpecialty");

        // Validation should throw ValidationException
        ValidationException exception = assertThrows(ValidationException.class, () -> duplicateValidator.validateDentistAppointment(invalidDTO));
        assertEquals("This patient already has an appointment on this date", exception.getMessage());

        // Verify repository method was called
        verify(mockDentistRepository).existsByPatientIdAndDateBetween(1, invalidDTO.date().withHour(7), invalidDTO.date().withHour(18));
    }

    @Test
    public void testValidateDoctorAppointment_NoExistingAppointment() {
        // Mock the repository to return false (no existing appointment)
        when(mockDoctorRepository.existsByPatientIdAndDateBetween(anyInt(), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(false);

        // Create a valid CreateDoctorAppointmentDTO
        CreateDoctorAppointmentDTO validDTO = new CreateDoctorAppointmentDTO(1, 1, LocalDateTime.now().plusDays(1), "someMedicalSpecialty");

        // Validation should pass without throwing exception
        assertDoesNotThrow(() -> duplicateValidator.validateDoctorAppointment(validDTO));

        // Verify repository method was called
        verify(mockDoctorRepository).existsByPatientIdAndDateBetween(1, validDTO.date().withHour(7), validDTO.date().withHour(18));
    }

    @Test
    public void testValidateDoctorAppointment_ExistingAppointment() {
        // Mock the repository to return true (existing appointment)
        when(mockDoctorRepository.existsByPatientIdAndDateBetween(anyInt(), any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(true);

        // Create an invalid CreateDoctorAppointmentDTO
        CreateDoctorAppointmentDTO invalidDTO = new CreateDoctorAppointmentDTO(1, 1, LocalDateTime.now().plusDays(1), "someMedicalSpecialty");

        // Validation should throw ValidationException
        ValidationException exception = assertThrows(ValidationException.class, () -> duplicateValidator.validateDoctorAppointment(invalidDTO));
        assertEquals("This patient already has an appointment on this date", exception.getMessage());

        // Verify repository method was called
        verify(mockDoctorRepository).existsByPatientIdAndDateBetween(1, invalidDTO.date().withHour(7), invalidDTO.date().withHour(18));
    }
}


