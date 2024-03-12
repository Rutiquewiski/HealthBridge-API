package br.rutiquewiski.HealthBridge.domain.appointment.validators;

import br.rutiquewiski.HealthBridge.domain.appointment.dentist.DTO.CreateDentistAppointmentDTO;
import br.rutiquewiski.HealthBridge.domain.appointment.doctor.DTO.CreateDoctorAppointmentDTO;
import br.rutiquewiski.HealthBridge.domain.professional.dentist.Dentist;
import br.rutiquewiski.HealthBridge.domain.professional.doctor.Doctor;
import br.rutiquewiski.HealthBridge.infra.errors.ValidationException;
import br.rutiquewiski.HealthBridge.repositories.DentistRepository;
import br.rutiquewiski.HealthBridge.repositories.DoctorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ActiveProfessionalValidatorTest {

    @Mock
    private DentistRepository mockDentistRepository;

    @Mock
    private DoctorRepository mockDoctorRepository;

    @InjectMocks
    private ActiveProfessionalValidator validator;

    @Test
    public void testValidateDentistAppointment() {
        // Create a valid CreateDentistAppointmentDTO
        CreateDentistAppointmentDTO validDTO = new CreateDentistAppointmentDTO(1, 1, LocalDateTime.now().plusDays(1), "someDentalSpecialty");

        // Create a valid dentist
        Dentist validDentist = new Dentist();
        validDentist.setId(1);
        validDentist.setActive(true);

        // Stubbing
        when(mockDentistRepository.getReferenceById(1)).thenReturn(validDentist);

        // Validation should pass without throwing exception
        assertDoesNotThrow(() -> validator.validateDentistAppointment(validDTO));

        // Create an invalid dentist
        Dentist invalidDentist = new Dentist();
        invalidDentist.setId(2); // Different ID
        invalidDentist.setActive(false);

        // Stubbing for invalid dentist
        when(mockDentistRepository.getReferenceById(2)).thenReturn(invalidDentist);

        // Create an invalid CreateDentistAppointmentDTO
        CreateDentistAppointmentDTO invalidDTO = new CreateDentistAppointmentDTO(2, 1, LocalDateTime.now().plusDays(1), "someDentalSpecialty");

        // Validation should throw ValidationException
        assertThrows(ValidationException.class, () -> validator.validateDentistAppointment(invalidDTO));
    }

    @Test
    public void testValidateDoctorAppointment() {
        // Create a valid CreateDoctorAppointmentDTO
        CreateDoctorAppointmentDTO validDTO = new CreateDoctorAppointmentDTO(1, 1, LocalDateTime.now().plusDays(1), "someMedicalSpecialty");

        // Create a valid doctor
        Doctor validDoctor = new Doctor();
        validDoctor.setId(1);
        validDoctor.setActive(true);

        // Stubbing
        when(mockDoctorRepository.getReferenceById(1)).thenReturn(validDoctor);

        // Validation should pass without throwing exception
        assertDoesNotThrow(() -> validator.validateDoctorAppointment(validDTO));

        // Create an invalid doctor
        Doctor invalidDoctor = new Doctor();
        invalidDoctor.setId(2); // Different ID
        invalidDoctor.setActive(false);

        // Stubbing for invalid doctor
        when(mockDoctorRepository.getReferenceById(2)).thenReturn(invalidDoctor);

        // Create an invalid CreateDoctorAppointmentDTO
        CreateDoctorAppointmentDTO invalidDTO = new CreateDoctorAppointmentDTO(2, 1, LocalDateTime.now().plusDays(1), "someMedicalSpecialty");

        // Validation should throw ValidationException
        assertThrows(ValidationException.class, () -> validator.validateDoctorAppointment(invalidDTO));
    }
}


