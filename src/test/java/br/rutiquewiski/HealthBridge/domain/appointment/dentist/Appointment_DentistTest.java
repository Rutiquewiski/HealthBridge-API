package br.rutiquewiski.HealthBridge.domain.appointment.dentist;

import br.rutiquewiski.HealthBridge.domain.patient.Patient;
import br.rutiquewiski.HealthBridge.domain.professional.dentist.Dentist;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class Appointment_DentistTest {

    @Test
    public void testAppointmentCreation() {
        // Create a mock Dentist and Patient
        Dentist mockDentist = mock(Dentist.class);
        Patient mockPatient = mock(Patient.class);

        // Create an appointment
        Appointment_Dentist appointment = new Appointment_Dentist();
        appointment.setDentist(mockDentist);
        appointment.setPatient(mockPatient);
        appointment.setDate(LocalDateTime.now());

        // Assert appointment is created successfully
        assertNotNull(appointment.getDentist());
        assertNotNull(appointment.getPatient());
        assertNotNull(appointment.getDate());
    }

    @Test
    public void testGettersAndSetters() {
        // Create a mock Dentist
        Dentist mockDentist = mock(Dentist.class);

        // Create an appointment
        Appointment_Dentist appointment = new Appointment_Dentist();
        appointment.setDentist(mockDentist);

        // Assert getters and setters
        assertEquals(mockDentist, appointment.getDentist());
    }

    @Test
    public void testEqualsAndHashCode() {
        // Create two appointments with same ID
        Appointment_Dentist appointment1 = new Appointment_Dentist(1, null, null, null);
        Appointment_Dentist appointment2 = new Appointment_Dentist(1, null, null, null);

        // Assert equals and hashcode
        assertEquals(appointment1, appointment2);
        assertEquals(appointment1.hashCode(), appointment2.hashCode());
    }

    @Test
    public void testDateManipulation() {
        // Create an appointment
        Appointment_Dentist appointment = new Appointment_Dentist();
        LocalDateTime now = LocalDateTime.now();
        appointment.setDate(now);

        // Assert date manipulation
        assertEquals(now, appointment.getDate());
    }
}

