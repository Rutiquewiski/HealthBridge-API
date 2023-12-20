package br.rutiquewiski.HealthBridge.controllers;

import br.rutiquewiski.HealthBridge.domain.appointment.AppointmentManager;
import br.rutiquewiski.HealthBridge.domain.appointment.doctor.DTO.CreateDoctorAppointmentDTO;
import br.rutiquewiski.HealthBridge.domain.appointment.doctor.DTO.CreatedDoctorAppointmentDTO;
import br.rutiquewiski.HealthBridge.infra.errors.ValidationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointment/doctor")
public class DoctorAppointmentController {

    @Autowired
    private AppointmentManager appointmentManager;

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
}
