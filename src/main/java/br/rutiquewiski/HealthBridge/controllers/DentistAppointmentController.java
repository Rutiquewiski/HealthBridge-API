package br.rutiquewiski.HealthBridge.controllers;

import br.rutiquewiski.HealthBridge.domain.appointment.AppointmentManager;
import br.rutiquewiski.HealthBridge.domain.appointment.dentist.DTO.CreateDentistAppointmentDTO;
import br.rutiquewiski.HealthBridge.domain.appointment.dentist.DTO.ListDentistAppointmentDTO;
import br.rutiquewiski.HealthBridge.infra.errors.ValidationException;
import br.rutiquewiski.HealthBridge.repositories.Appointment_DentistRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointment/dentist")
public class DentistAppointmentController {

    @Autowired
    private AppointmentManager appointmentManager;

    @PostMapping
    public ResponseEntity<ListDentistAppointmentDTO> createDentistAppointment(@RequestBody @Valid CreateDentistAppointmentDTO createDentistAppointmentDTO) throws ValidationException {

        ListDentistAppointmentDTO listDentistAppointmentDTO = appointmentManager.createDentistAppointment(createDentistAppointmentDTO);
        return ResponseEntity.ok(listDentistAppointmentDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelDentistAppointment(@PathVariable Integer id) {

        appointmentManager.cancelDentistAppointment(id);

        return ResponseEntity.ok("Appointment canceled");
    }
}
