package br.rutiquewiski.HealthBridge.controllers;

import br.rutiquewiski.HealthBridge.domain.pacient.DTO.PacientDetailsDTO;
import br.rutiquewiski.HealthBridge.domain.pacient.DTO.PacientListingDTO;
import br.rutiquewiski.HealthBridge.domain.pacient.DTO.PacientRegistrationDTO;
import br.rutiquewiski.HealthBridge.domain.pacient.DTO.PacientUpdateDTO;
import br.rutiquewiski.HealthBridge.domain.pacient.Pacient;
import br.rutiquewiski.HealthBridge.repositories.PacientRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("api/v1/pacient")
public class PacientController {

    @Autowired
    PacientRepository pacientRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> registerPacient(@RequestBody @Valid PacientRegistrationDTO pacientRegistrationDTO, UriComponentsBuilder uriComponentsBuilder){

        var pacient = new Pacient(pacientRegistrationDTO);

        pacientRepository.save(pacient);

        var uri = uriComponentsBuilder.path("/pacient/{id}").buildAndExpand(pacient.getId()).toUri();

        return ResponseEntity.ok(uri);
    }

    @GetMapping
    public ResponseEntity<?> getAllPacients(Pageable pageable) {

        Page<PacientListingDTO> pacients = pacientRepository.findAllByActiveTrue(pageable).map(PacientListingDTO::new);

        if (pacients.isEmpty()) {

            return ResponseEntity.ok("Empty list");

        } else {

            return ResponseEntity.ok(pacients);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPacientDetails(@PathVariable Integer id) {

        Pacient pacient = pacientRepository.findByIdAndActiveTrue(id);

        if (pacient == null) {

            return ResponseEntity.badRequest().body("Invalid information");

        } else {

            return ResponseEntity.ok(new PacientDetailsDTO(pacient));
        }
    }

    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePacient(@PathVariable Integer id, @RequestBody PacientUpdateDTO pacientUpdateDTO) {

        Pacient pacient = pacientRepository.findByIdAndActiveTrue(id);

        if (pacient == null) {

            return ResponseEntity.badRequest().body("Invalid information");

        } else {

            pacient.updateInformation(pacientUpdateDTO);
            pacientRepository.save(pacient);
            return ResponseEntity.ok("Pacient information updated");
        }
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePacient(@PathVariable Integer id) {

        Pacient pacient = pacientRepository.findByIdAndActiveTrue(id);

        if (pacient == null) {

            return ResponseEntity.badRequest().body("Invalid information");

        } else {

            pacient.safeDelete();
            pacientRepository.save(pacient);
            return ResponseEntity.ok("Pacient deleted");
        }
    }
}
