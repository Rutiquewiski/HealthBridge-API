package br.rutiquewiski.HealthBridge.controllers;

import br.rutiquewiski.HealthBridge.domain.appointment.AppointmentManager;
import br.rutiquewiski.HealthBridge.domain.appointment.dentist.Appointment_Dentist;
import br.rutiquewiski.HealthBridge.domain.appointment.dentist.DTO.CreateDentistAppointmentDTO;
import br.rutiquewiski.HealthBridge.domain.appointment.dentist.DTO.CreatedDentistAppointmentDTO;
import br.rutiquewiski.HealthBridge.domain.patient.Patient;
import br.rutiquewiski.HealthBridge.domain.professional.dentist.Dentist;
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
public class DentistAppointmentControllerTest {

    @Mock
    private AppointmentManager appointmentManager;

    @InjectMocks
    private DentistAppointmentController dentistAppointmentController;

    @Test
    public void testCreateDentistAppointment_Valid() throws ValidationException {
        // Mock appointment manager response
        Appointment_Dentist appointment_dentist = new Appointment_Dentist(1, new Dentist(), new Patient(), LocalDateTime.now());
        CreatedDentistAppointmentDTO dto = new CreatedDentistAppointmentDTO(appointment_dentist);
        when(appointmentManager.createDentistAppointment(any())).thenReturn(dto);

        // Valid DTO
        CreateDentistAppointmentDTO validDTO = new CreateDentistAppointmentDTO(1, 1, LocalDateTime.now().plusDays(1), "someDentalSpecialty");

        // Test controller method
        ResponseEntity<CreatedDentistAppointmentDTO> response = dentistAppointmentController.createDentistAppointment(validDTO);

        // Assertions
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(dto, response.getBody());
    }

    @Test
    public void testCancelDentistAppointment_Valid() {
        // Mock appointment cancellation
        doNothing().when(appointmentManager).cancelDentistAppointment(anyInt());

        // Test controller method
        ResponseEntity<String> response = dentistAppointmentController.cancelDentistAppointment(1);

        // Assertions
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Appointment canceled", response.getBody());
    }

}


