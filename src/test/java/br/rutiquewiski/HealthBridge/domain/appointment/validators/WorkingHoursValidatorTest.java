package br.rutiquewiski.HealthBridge.domain.appointment.validators;

import br.rutiquewiski.HealthBridge.domain.appointment.dentist.DTO.CreateDentistAppointmentDTO;
import br.rutiquewiski.HealthBridge.domain.appointment.doctor.DTO.CreateDoctorAppointmentDTO;
import br.rutiquewiski.HealthBridge.infra.errors.ValidationException;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class WorkingHoursValidatorTest {

    @Test
    public void testValidateDentistAppointment_Valid() {
        // Create a valid CreateDentistAppointmentDTO for a weekday, within working hours
        LocalDateTime validWeekdayMorning = LocalDateTime.of(2024, 3, 14, 9, 0); // Tuesday morning
        CreateDentistAppointmentDTO validWeekdayMorningDTO = new CreateDentistAppointmentDTO(1, 1, validWeekdayMorning, "someDentalSpecialty");

        // Mock appointment creation validator
        WorkingHoursValidator validator = new WorkingHoursValidator();

        // Validation should pass without throwing exception
        assertDoesNotThrow(() -> validator.validateDentistAppointment(validWeekdayMorningDTO));

        // Create a valid CreateDentistAppointmentDTO for a weekday, within working hours
        LocalDateTime validWeekdayEvening = LocalDateTime.of(2024, 3, 14, 17, 0); // Tuesday evening
        CreateDentistAppointmentDTO validWeekdayEveningDTO = new CreateDentistAppointmentDTO(1, 1, validWeekdayEvening, "someDentalSpecialty");

        // Validation should pass without throwing exception
        assertDoesNotThrow(() -> validator.validateDentistAppointment(validWeekdayEveningDTO));

    }

    @Test
    public void testValidateDentistAppointment_Invalid() {
        // Create an invalid CreateDentistAppointmentDTO for Sunday
        LocalDateTime invalidSunday = LocalDateTime.of(2024, 3, 17, 12, 0); // Sunday
        CreateDentistAppointmentDTO invalidSundayDTO = new CreateDentistAppointmentDTO(1, 1, invalidSunday, "someDentalSpecialty");

        // Mock appointment creation validator
        WorkingHoursValidator validator = new WorkingHoursValidator();

        // Validation should throw ValidationException
        assertThrows(ValidationException.class, () -> validator.validateDentistAppointment(invalidSundayDTO));

        // Create an invalid CreateDentistAppointmentDTO for a weekday, before working hours
        LocalDateTime invalidBeforeHours = LocalDateTime.of(2024, 3, 14, 6, 59); // Tuesday before 7 AM
        CreateDentistAppointmentDTO invalidBeforeHoursDTO = new CreateDentistAppointmentDTO(1, 1, invalidBeforeHours, "someDentalSpecialty");

        // Validation should throw ValidationException
        assertThrows(ValidationException.class, () -> validator.validateDentistAppointment(invalidBeforeHoursDTO));

        // Create an invalid CreateDentistAppointmentDTO for a weekday, after working hours
        LocalDateTime invalidAfterHours = LocalDateTime.of(2024, 3, 14, 19, 30); // Tuesday after 6 PM
        CreateDentistAppointmentDTO invalidAfterHoursDTO = new CreateDentistAppointmentDTO(1, 1, invalidAfterHours, "someDentalSpecialty");

        // Validation should throw ValidationException
        assertThrows(ValidationException.class, () -> validator.validateDentistAppointment(invalidAfterHoursDTO));
    }

    @Test
    public void testValidateDoctorAppointment_Valid() {
        // Create a valid CreateDoctorAppointmentDTO for a weekday, within working hours
        LocalDateTime validWeekdayMorning = LocalDateTime.of(2024, 3, 14, 9, 0); // Tuesday morning
        CreateDoctorAppointmentDTO validWeekdayMorningDTO = new CreateDoctorAppointmentDTO(1, 1, validWeekdayMorning, "someMedicalSpecialty");

        // Mock appointment creation validator
        WorkingHoursValidator validator = new WorkingHoursValidator();

        // Validation should pass without throwing exception
        assertDoesNotThrow(() -> validator.validateDoctorAppointment(validWeekdayMorningDTO));

        // Create a valid CreateDoctorAppointmentDTO for a weekday, within working hours
        LocalDateTime validWeekdayEvening = LocalDateTime.of(2024, 3, 14, 17, 0); // Tuesday evening
        CreateDoctorAppointmentDTO validWeekdayEveningDTO = new CreateDoctorAppointmentDTO(1, 1, validWeekdayEvening, "someMedicalSpecialty");

        // Validation should pass without throwing exception
        assertDoesNotThrow(() -> validator.validateDoctorAppointment(validWeekdayEveningDTO));

    }

    @Test
    public void testValidateDoctorAppointment_Invalid() {
        // Create an invalid CreateDoctorAppointmentDTO for Sunday
        LocalDateTime invalidSunday = LocalDateTime.of(2024, 3, 17, 12, 0); // Sunday
        CreateDoctorAppointmentDTO invalidSundayDTO = new CreateDoctorAppointmentDTO(1, 1, invalidSunday, "someMedicalSpecialty");

        // Mock appointment creation validator
        WorkingHoursValidator validator = new WorkingHoursValidator();

        // Validation should throw ValidationException
        assertThrows(ValidationException.class, () -> validator.validateDoctorAppointment(invalidSundayDTO));

        // Create an invalid CreateDoctorAppointmentDTO for a weekday, before working hours
        LocalDateTime invalidBeforeHours = LocalDateTime.of(2024, 3, 14, 6, 59); // Tuesday before 7 AM
        CreateDoctorAppointmentDTO invalidBeforeHoursDTO = new CreateDoctorAppointmentDTO(1, 1, invalidBeforeHours, "someMedicalSpecialty");

        // Validation should throw ValidationException
        assertThrows(ValidationException.class, () -> validator.validateDoctorAppointment(invalidBeforeHoursDTO));

        // Create an invalid CreateDoctorAppointmentDTO for a weekday, after working hours
        LocalDateTime invalidAfterHours = LocalDateTime.of(2024, 3, 14, 19, 30); // Tuesday after 6 PM
        CreateDoctorAppointmentDTO invalidAfterHoursDTO = new CreateDoctorAppointmentDTO(1, 1, invalidAfterHours, "someMedicalSpecialty");

        // Validation should throw ValidationException
        assertThrows(ValidationException.class, () -> validator.validateDoctorAppointment(invalidAfterHoursDTO));
    }
}

