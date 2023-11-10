package br.rutiquewiski.HealthBridge.controllers;

import br.rutiquewiski.HealthBridge.domain.professional.doctor.MedicalSpecialty;
import br.rutiquewiski.HealthBridge.repositories.MedicalSpecialtyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/medical-specialties")
public class MedicalSpecialtyController {

    @Autowired
    MedicalSpecialtyRepository medicalSpecialtyRepository;

    @GetMapping
    public ResponseEntity<Page<MedicalSpecialty>> getMedicalSpecialties(Pageable pageable) {

        Page<MedicalSpecialty> medicalSpecialties = medicalSpecialtyRepository.findAll(pageable);

        return ResponseEntity.ok(medicalSpecialties);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> registerMedicalSpecialty(@RequestBody MedicalSpecialty medicalSpecialty) {

        var medicalSpecialtyExists = medicalSpecialtyRepository.findByName(medicalSpecialty.getName());

        if (medicalSpecialtyExists != null) {

            return ResponseEntity.badRequest().body("Medical specialty already exists");

        } else {

            medicalSpecialtyRepository.save(medicalSpecialty);

            return ResponseEntity.ok("Medical specialty registered successfully");

        }
    }

    @DeleteMapping("/{medicalSpecialtyName}")
    @Transactional
    public ResponseEntity<?> deleteMedicalSpecialty(@PathVariable String medicalSpecialtyName) {

        var medicalSpecialtyExists = medicalSpecialtyRepository.findByName(medicalSpecialtyName);

        if (medicalSpecialtyExists != null) {

            medicalSpecialtyRepository.delete(medicalSpecialtyExists);

            return ResponseEntity.ok("Medical specialty deleted successfully");

        } else {

            return ResponseEntity.badRequest().body("Medical specialty not found");

        }
    }
}
