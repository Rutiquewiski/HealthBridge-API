package br.rutiquewiski.HealthBridge.controllers;

import br.rutiquewiski.HealthBridge.domain.professional.dentist.DentalSpecialty;
import br.rutiquewiski.HealthBridge.repositories.DentalSpecialtyRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/dental-specialties")
@SecurityRequirement(name = "bearer-key")
public class DentalSpecialtyController {

    @Autowired
    DentalSpecialtyRepository dentalSpecialtyRepository;

    @GetMapping
    public ResponseEntity<Page<DentalSpecialty>> getDentalSpecialties(Pageable  pageable) {

        Page<DentalSpecialty> dentalSpecialties = dentalSpecialtyRepository.findAll(pageable);

        return ResponseEntity.ok(dentalSpecialties);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> registerDentalSpecialty(@RequestBody DentalSpecialty dentalSpecialty) {

        var dentalSpecialtyExists = dentalSpecialtyRepository.findByName(dentalSpecialty.getName());

        if (dentalSpecialtyExists != null) {

            return ResponseEntity.badRequest().body("Dental specialty already exists");

        } else {

            dentalSpecialtyRepository.save(dentalSpecialty);

            return ResponseEntity.ok("Dental specialty registered successfully");

        }
    }

    @DeleteMapping("/{dentalSpecialtyName}")
    @Transactional
    public ResponseEntity<?> deleteDentalSpecialty(@PathVariable String dentalSpecialtyName) {

        var dentalSpecialtyExists = dentalSpecialtyRepository.findByName(dentalSpecialtyName);

        if (dentalSpecialtyExists != null) {

            dentalSpecialtyRepository.delete(dentalSpecialtyExists);

            return ResponseEntity.ok("Dental specialty deleted successfully");

        } else {

            return ResponseEntity.badRequest().body("Dental specialty not found");

        }
    }
}
