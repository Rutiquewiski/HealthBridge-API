package br.rutiquewiski.HealthBridge.controllers;

import br.rutiquewiski.HealthBridge.domain.appointment.AppointmentManager;
import br.rutiquewiski.HealthBridge.domain.appointment.doctor.Appointment_Doctor;
import br.rutiquewiski.HealthBridge.domain.appointment.doctor.DTO.CreateDoctorAppointmentDTO;
import br.rutiquewiski.HealthBridge.domain.appointment.doctor.DTO.CreatedDoctorAppointmentDTO;
import br.rutiquewiski.HealthBridge.domain.patient.Patient;
import br.rutiquewiski.HealthBridge.domain.professional.doctor.Doctor;
import br.rutiquewiski.HealthBridge.infra.errors.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DoctorAppointmentControllerTest {

    @Mock
    private AppointmentManager appointmentManager;

    @InjectMocks
    private DoctorAppointmentController doctorAppointmentController;

    @Test
    public void testCreateDoctorAppointment_Valid() throws ValidationException {
        // Mock appointment manager response
        Appointment_Doctor appointment_doctor = new Appointment_Doctor(1, new Doctor(), new Patient(), LocalDateTime.now());
        CreatedDoctorAppointmentDTO dto = new CreatedDoctorAppointmentDTO(appointment_doctor);
        when(appointmentManager.createDoctorAppointment(any())).thenReturn(dto);

        // Valid DTO
        CreateDoctorAppointmentDTO validDTO = new CreateDoctorAppointmentDTO(1, 1, LocalDateTime.now().plusDays(1), "someMedicalSpecialty");

        // Test controller method
        ResponseEntity<CreatedDoctorAppointmentDTO> response = doctorAppointmentController.createDoctorAppointment(validDTO);

        // Assertions
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(dto, response.getBody());
    }

    @Test
    public void testCancelDoctorAppointment_Valid() {
        // Mock appointment cancellation
        doNothing().when(appointmentManager).cancelDoctorAppointment(anyInt());

        // Test controller method
        ResponseEntity<String> response = doctorAppointmentController.cancelDoctorAppointment(1);

        // Assertions
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Appointment canceled", response.getBody());
    }

}

