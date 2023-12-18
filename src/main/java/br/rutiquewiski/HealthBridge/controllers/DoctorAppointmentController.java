package br.rutiquewiski.HealthBridge.controllers;

import br.rutiquewiski.HealthBridge.domain.appointment.AppointmentManager;
import br.rutiquewiski.HealthBridge.domain.appointment.doctor.DTO.CreateDoctorAppointmentDTO;
import br.rutiquewiski.HealthBridge.domain.appointment.doctor.DTO.ListDoctorAppointmentDTO;
import br.rutiquewiski.HealthBridge.infra.errors.ValidationException;
import br.rutiquewiski.HealthBridge.repositories.Appointment_DoctorRepository;
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
    public ResponseEntity<ListDoctorAppointmentDTO> createDoctorAppointment(@RequestBody @Valid CreateDoctorAppointmentDTO createDoctorAppointmentDTO) throws ValidationException {

        ListDoctorAppointmentDTO listDoctorAppointmentDTO = appointmentManager.createDoctorAppointment(createDoctorAppointmentDTO);
        return ResponseEntity.ok(listDoctorAppointmentDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelDoctorAppointment(@PathVariable Integer id) {

        appointmentManager.cancelDoctorAppointment(id);

        return ResponseEntity.ok("Appointment canceled");
    }
}
