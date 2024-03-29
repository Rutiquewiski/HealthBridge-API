package br.rutiquewiski.HealthBridge.controllers;

import br.rutiquewiski.HealthBridge.domain.patient.DTO.PatientDetailsDTO;
import br.rutiquewiski.HealthBridge.domain.patient.DTO.PatientListingDTO;
import br.rutiquewiski.HealthBridge.domain.patient.DTO.PatientRegistrationDTO;
import br.rutiquewiski.HealthBridge.domain.patient.DTO.PatientUpdateDTO;
import br.rutiquewiski.HealthBridge.domain.patient.Patient;
import br.rutiquewiski.HealthBridge.repositories.PatientRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("api/patient")
@SecurityRequirement(name = "bearer-key")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> registerPatient(@RequestBody @Valid PatientRegistrationDTO patientRegistrationDTO, UriComponentsBuilder uriComponentsBuilder){

        var patient = new Patient(patientRegistrationDTO);

        patientRepository.save(patient);

        var uri = uriComponentsBuilder.path("/api/patient/{id}").buildAndExpand(patient.getId()).toUri();

        return ResponseEntity.ok(uri);
    }

    @GetMapping
    public ResponseEntity<?> getAllPatients(@RequestParam(required = false) String name, Pageable pageable) {

        Page<PatientListingDTO> patients;

        if (name != null) {


            patients = patientRepository.findAllByActiveTrueAndNameContaining(pageable, name).map(PatientListingDTO::new);

        } else {

            patients  = patientRepository.findAllByActiveTrue(pageable).map(PatientListingDTO::new);
        }

        if (patients.isEmpty()) {

            return ResponseEntity.ok("Empty list");

        } else {

            return ResponseEntity.ok(patients);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPatientDetails(@PathVariable Integer id) {

        Patient patient = patientRepository.findByIdAndActiveTrue(id);

        if (patient == null) {

            return ResponseEntity.badRequest().body("Invalid id");

        } else {

            return ResponseEntity.ok(new PatientDetailsDTO(patient));
        }
    }

    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePatient(@PathVariable Integer id, @RequestBody PatientUpdateDTO patientUpdateDTO) {

        Patient patient = patientRepository.findByIdAndActiveTrue(id);

        if (patient == null) {

            return ResponseEntity.badRequest().body("Invalid id");

        } else {

            patient.updateInformation(patientUpdateDTO);
            patientRepository.save(patient);
            return ResponseEntity.ok("Patient information updated");
        }
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable Integer id) {

        Patient patient = patientRepository.findByIdAndActiveTrue(id);

        if (patient == null) {

            return ResponseEntity.badRequest().body("Invalid id");

        } else {

            patient.safeDelete();
            patientRepository.save(patient);
            return ResponseEntity.ok("Patient deleted");
        }
    }
}
