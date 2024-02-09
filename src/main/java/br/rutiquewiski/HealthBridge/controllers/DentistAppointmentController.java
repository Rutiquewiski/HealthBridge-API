package br.rutiquewiski.HealthBridge.controllers;

import br.rutiquewiski.HealthBridge.domain.appointment.AppointmentManager;
import br.rutiquewiski.HealthBridge.domain.appointment.dentist.DTO.CreateDentistAppointmentDTO;
import br.rutiquewiski.HealthBridge.domain.appointment.dentist.DTO.CreatedDentistAppointmentDTO;
import br.rutiquewiski.HealthBridge.infra.errors.ValidationException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointment/dentist")
@SecurityRequirement(name = "bearer-key")
public class DentistAppointmentController {

    @Autowired
    private AppointmentManager appointmentManager;

    @PostMapping
    public ResponseEntity<CreatedDentistAppointmentDTO> createDentistAppointment(@RequestBody @Valid CreateDentistAppointmentDTO createDentistAppointmentDTO) throws ValidationException {

        CreatedDentistAppointmentDTO createdDentistAppointmentDTO1 = appointmentManager.createDentistAppointment(createDentistAppointmentDTO);
        return ResponseEntity.ok(createdDentistAppointmentDTO1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelDentistAppointment(@PathVariable Integer id) {

        appointmentManager.cancelDentistAppointment(id);

        return ResponseEntity.ok("Appointment canceled");
    }
}
