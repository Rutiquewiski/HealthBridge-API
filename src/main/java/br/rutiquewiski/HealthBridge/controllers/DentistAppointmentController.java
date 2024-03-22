package br.rutiquewiski.HealthBridge.controllers;

import br.rutiquewiski.HealthBridge.domain.appointment.AppointmentManager;
import br.rutiquewiski.HealthBridge.domain.appointment.dentist.DTO.CreateDentistAppointmentDTO;
import br.rutiquewiski.HealthBridge.domain.appointment.dentist.DTO.CreatedDentistAppointmentDTO;
import br.rutiquewiski.HealthBridge.domain.appointment.dentist.DTO.DentistAppointmentListingDTO;
import br.rutiquewiski.HealthBridge.infra.errors.ValidationException;
import br.rutiquewiski.HealthBridge.repositories.AppointmentDentistRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointment/dentist")
@SecurityRequirement(name = "bearer-key")
public class DentistAppointmentController {

    @Autowired
    private AppointmentManager appointmentManager;

    @Autowired
    private AppointmentDentistRepository appointmentDentistRepository;

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

    @GetMapping
    public ResponseEntity<?> getAllAppointments(Pageable pageable) {

        Page<DentistAppointmentListingDTO> appointments;

        appointments = appointmentDentistRepository.findAll(pageable).map(DentistAppointmentListingDTO::new);

        if (appointments.isEmpty()) {

            return ResponseEntity.ok("Empty List");

        } else {

            return ResponseEntity.ok(appointments);
        }
    }
}
