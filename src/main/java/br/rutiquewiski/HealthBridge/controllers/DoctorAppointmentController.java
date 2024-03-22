package br.rutiquewiski.HealthBridge.controllers;

import br.rutiquewiski.HealthBridge.domain.appointment.AppointmentManager;
import br.rutiquewiski.HealthBridge.domain.appointment.dentist.DTO.DentistAppointmentListingDTO;
import br.rutiquewiski.HealthBridge.domain.appointment.doctor.DTO.CreateDoctorAppointmentDTO;
import br.rutiquewiski.HealthBridge.domain.appointment.doctor.DTO.CreatedDoctorAppointmentDTO;
import br.rutiquewiski.HealthBridge.domain.appointment.doctor.DTO.DoctorAppointmentListingDTO;
import br.rutiquewiski.HealthBridge.infra.errors.ValidationException;
import br.rutiquewiski.HealthBridge.repositories.AppointmentDoctorRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointment/doctor")
@SecurityRequirement(name = "bearer-key")
public class DoctorAppointmentController {

    @Autowired
    private AppointmentManager appointmentManager;

    @Autowired
    private AppointmentDoctorRepository appointmentDoctorRepository;

    @PostMapping
    public ResponseEntity<CreatedDoctorAppointmentDTO> createDoctorAppointment(@RequestBody @Valid CreateDoctorAppointmentDTO createDoctorAppointmentDTO) throws ValidationException {

        CreatedDoctorAppointmentDTO createdDoctorAppointmentDTO = appointmentManager.createDoctorAppointment(createDoctorAppointmentDTO);
        return ResponseEntity.ok(createdDoctorAppointmentDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelDoctorAppointment(@PathVariable Integer id) {

        appointmentManager.cancelDoctorAppointment(id);

        return ResponseEntity.ok("Appointment canceled");
    }

    @GetMapping
    public ResponseEntity<?> getAllAppointments(Pageable pageable) {

        Page<DoctorAppointmentListingDTO> appointments;

        appointments = appointmentDoctorRepository.findAll(pageable).map(DoctorAppointmentListingDTO::new);

        if (appointments.isEmpty()) {

            return ResponseEntity.ok("Empty List");

        } else {

            return ResponseEntity.ok(appointments);
        }
    }
}
