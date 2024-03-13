package br.rutiquewiski.HealthBridge.domain.appointment.doctor;

import br.rutiquewiski.HealthBridge.domain.patient.Patient;
import br.rutiquewiski.HealthBridge.domain.professional.doctor.Doctor;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class Appointment_DoctorTest {

    @Test
    public void testAppointmentCreation() {
        // Create a mock Doctor and Patient
        Doctor mockDoctor = mock(Doctor.class);
        Patient mockPatient = mock(Patient.class);

        // Create an appointment
        Appointment_Doctor appointment = new Appointment_Doctor();
        appointment.setDoctor(mockDoctor);
        appointment.setPatient(mockPatient);
        appointment.setDate(LocalDateTime.now());

        // Assert appointment is created successfully
        assertNotNull(appointment.getDoctor());
        assertNotNull(appointment.getPatient());
        assertNotNull(appointment.getDate());
    }

    @Test
    public void testGettersAndSetters() {
        // Create a mock Doctor
        Doctor mockDoctor = mock(Doctor.class);

        // Create an appointment
        Appointment_Doctor appointment = new Appointment_Doctor();
        appointment.setDoctor(mockDoctor);

        // Assert getters and setters
        assertEquals(mockDoctor, appointment.getDoctor());
    }

    @Test
    public void testEqualsAndHashCode() {
        // Create two appointments with same ID
        Appointment_Doctor appointment1 = new Appointment_Doctor(1, null, null, null);
        Appointment_Doctor appointment2 = new Appointment_Doctor(1, null, null, null);

        // Assert equals and hashcode
        assertEquals(appointment1, appointment2);
        assertEquals(appointment1.hashCode(), appointment2.hashCode());
    }

    @Test
    public void testDateManipulation() {
        // Create an appointment
        Appointment_Doctor appointment = new Appointment_Doctor();
        LocalDateTime now = LocalDateTime.now();
        appointment.setDate(now);

        // Assert date manipulation
        assertEquals(now, appointment.getDate());
    }
}

