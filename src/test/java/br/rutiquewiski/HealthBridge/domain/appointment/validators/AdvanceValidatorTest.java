package br.rutiquewiski.HealthBridge.domain.appointment.validators;

import br.rutiquewiski.HealthBridge.domain.appointment.dentist.DTO.CreateDentistAppointmentDTO;
import br.rutiquewiski.HealthBridge.domain.appointment.doctor.DTO.CreateDoctorAppointmentDTO;
import br.rutiquewiski.HealthBridge.infra.errors.ValidationException;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class AdvanceValidatorTest {

    @Test
    public void testValidateDentistAppointment() {
        // Create AdvanceValidator instance
        AdvanceValidator validator = new AdvanceValidator();

        // Create a valid CreateDentistAppointmentDTO with a date 31 minutes in advance
        LocalDateTime futureDate = LocalDateTime.now().plusMinutes(31);
        CreateDentistAppointmentDTO validDTO = new CreateDentistAppointmentDTO(1, 1, futureDate, "someDentalSpecialty");

        // Validation should pass without throwing exception
        assertDoesNotThrow(() -> validator.validateDentistAppointment(validDTO));

        // Create an invalid CreateDentistAppointmentDTO with a date less than 30 minutes in advance
        LocalDateTime pastDate = LocalDateTime.now().plusMinutes(29);
        CreateDentistAppointmentDTO invalidDTO = new CreateDentistAppointmentDTO(1, 1, pastDate, "someDentalSpecialty");

        // Validation should throw ValidationException
        assertThrows(ValidationException.class, () -> validator.validateDentistAppointment(invalidDTO));
    }

    @Test
    public void testValidateDoctorAppointment() {
        // Create AdvanceValidator instance
        AdvanceValidator validator = new AdvanceValidator();

        // Create a valid CreateDoctorAppointmentDTO with a date 31 minutes in advance
        LocalDateTime futureDate = LocalDateTime.now().plusMinutes(31);
        CreateDoctorAppointmentDTO validDTO = new CreateDoctorAppointmentDTO(1, 1, futureDate, "someMedicalSpecialty");

        // Validation should pass without throwing exception
        assertDoesNotThrow(() -> validator.validateDoctorAppointment(validDTO));

        // Create an invalid CreateDoctorAppointmentDTO with a date less than 30 minutes in advance
        LocalDateTime pastDate = LocalDateTime.now().plusMinutes(29);
        CreateDoctorAppointmentDTO invalidDTO = new CreateDoctorAppointmentDTO(1, 1, pastDate, "someMedicalSpecialty");

        // Validation should throw ValidationException
        assertThrows(ValidationException.class, () -> validator.validateDoctorAppointment(invalidDTO));
    }
}

