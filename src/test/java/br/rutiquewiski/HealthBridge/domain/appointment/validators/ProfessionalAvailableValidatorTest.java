package br.rutiquewiski.HealthBridge.domain.appointment.validators;

import br.rutiquewiski.HealthBridge.domain.appointment.dentist.DTO.CreateDentistAppointmentDTO;
import br.rutiquewiski.HealthBridge.domain.appointment.doctor.DTO.CreateDoctorAppointmentDTO;
import br.rutiquewiski.HealthBridge.infra.errors.ValidationException;
import br.rutiquewiski.HealthBridge.repositories.AppointmentDentistRepository;
import br.rutiquewiski.HealthBridge.repositories.AppointmentDoctorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProfessionalAvailableValidatorTest {

    @Mock
    private AppointmentDentistRepository mockDentistRepository;

    @Mock
    private AppointmentDoctorRepository mockDoctorRepository;

    @InjectMocks
    private ProfessionalAvailableValidator validator;

    @Test
    public void testValidateDentistAppointment() {
        // Create a valid CreateDentistAppointmentDTO
        CreateDentistAppointmentDTO validDTO = new CreateDentistAppointmentDTO(1, 1, LocalDateTime.now().plusDays(1), "someDentalSpecialty");

        // Stubbing for no existing appointment
        when(mockDentistRepository.existsByDentistIdAndDate(anyInt(), any(LocalDateTime.class)))
                .thenReturn(false);

        // Validation should pass without throwing exception
        assertDoesNotThrow(() -> validator.validateDentistAppointment(validDTO));

        // Verify method invocation
        verify(mockDentistRepository).existsByDentistIdAndDate(1, validDTO.date());
    }

    @Test
    public void testValidateDentistAppointment_ExistingAppointment() {
        // Create an invalid CreateDentistAppointmentDTO
        CreateDentistAppointmentDTO invalidDTO = new CreateDentistAppointmentDTO(1, 1, LocalDateTime.now().plusDays(1), "someDentalSpecialty");

        // Stubbing for existing appointment
        when(mockDentistRepository.existsByDentistIdAndDate(anyInt(), any(LocalDateTime.class)))
                .thenReturn(true);

        // Validation should throw ValidationException
        ValidationException exception = assertThrows(ValidationException.class, () -> validator.validateDentistAppointment(invalidDTO));
        assertEquals("Dentist unavailable", exception.getMessage());

        // Verify method invocation
        verify(mockDentistRepository).existsByDentistIdAndDate(1, invalidDTO.date());
    }

    @Test
    public void testValidateDoctorAppointment() {
        // Create a valid CreateDoctorAppointmentDTO
        CreateDoctorAppointmentDTO validDTO = new CreateDoctorAppointmentDTO(1, 1, LocalDateTime.now().plusDays(1), "someMedicalSpecialty");

        // Stubbing for no existing appointment
        when(mockDoctorRepository.existsByDoctorIdAndDate(anyInt(), any(LocalDateTime.class)))
                .thenReturn(false);

        // Validation should pass without throwing exception
        assertDoesNotThrow(() -> validator.validateDoctorAppointment(validDTO));

        // Verify method invocation
        verify(mockDoctorRepository).existsByDoctorIdAndDate(1, validDTO.date());
    }

    @Test
    public void testValidateDoctorAppointment_ExistingAppointment() {
        // Create an invalid CreateDoctorAppointmentDTO
        CreateDoctorAppointmentDTO invalidDTO = new CreateDoctorAppointmentDTO(1, 1, LocalDateTime.now().plusDays(1), "someMedicalSpecialty");

        // Stubbing for existing appointment
        when(mockDoctorRepository.existsByDoctorIdAndDate(anyInt(), any(LocalDateTime.class)))
                .thenReturn(true);

        // Validation should throw ValidationException
        ValidationException exception = assertThrows(ValidationException.class, () -> validator.validateDoctorAppointment(invalidDTO));
        assertEquals("Doctor unavailable", exception.getMessage());

        // Verify method invocation
        verify(mockDoctorRepository).existsByDoctorIdAndDate(1, invalidDTO.date());
    }
}

