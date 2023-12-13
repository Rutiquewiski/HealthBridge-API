package br.rutiquewiski.HealthBridge.controllers;

import br.rutiquewiski.HealthBridge.domain.professional.dentist.DTO.DentistDetailsDTO;
import br.rutiquewiski.HealthBridge.domain.professional.dentist.DTO.DentistUpdateDTO;
import br.rutiquewiski.HealthBridge.domain.professional.dentist.Dentist;
import br.rutiquewiski.HealthBridge.domain.professional.dentist.DTO.DentistListingDTO;
import br.rutiquewiski.HealthBridge.domain.professional.dentist.DTO.DentistRegistrationDTO;
import br.rutiquewiski.HealthBridge.repositories.DentistRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("api/dentist")
public class DentistController {

    @Autowired
    private DentistRepository dentistRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> registerDentist(@RequestBody @Valid DentistRegistrationDTO dentistRegistrationDTO, UriComponentsBuilder uriComponentsBuilder){

        var dentist = new Dentist(dentistRegistrationDTO);

        dentistRepository.save(dentist);

        var uri = uriComponentsBuilder.path("/api/dentist/{id}").buildAndExpand(dentist.getId()).toUri();

        return ResponseEntity.ok(uri);
    }

    @GetMapping
    public ResponseEntity<?> getAllDentists(@RequestParam(required = false) String name, Pageable pageable) {

        Page<DentistListingDTO> dentists;

        if (name != null) {

            dentists = dentistRepository.findAllByActiveTrueAndNameContaining(pageable, name).map(DentistListingDTO::new);

        } else {

            dentists = dentistRepository.findAllByActiveTrue(pageable).map(DentistListingDTO::new);
        }


        if (dentists.isEmpty()) {

            return ResponseEntity.ok("Empty list");

        } else {

            return ResponseEntity.ok(dentists);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDentistDetails(@PathVariable Integer id) {

        Dentist dentist = dentistRepository.findByIdAndActiveTrue(id);

        if (dentist == null) {

            return ResponseEntity.badRequest().body("Invalid information");

        } else {

            return ResponseEntity.ok(new DentistDetailsDTO(dentist));
        }
    }

    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateDentist(@PathVariable Integer id, @RequestBody DentistUpdateDTO dentistUpdateDTO) {

        Dentist dentist = dentistRepository.findByIdAndActiveTrue(id);

        if (dentist == null) {

            return ResponseEntity.badRequest().body("Invalid information");

        } else {

            dentist.updateInformation(dentistUpdateDTO);
            dentistRepository.save(dentist);
            return ResponseEntity.ok("Dentist information updated");
        }
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDentist(@PathVariable Integer id) {

        Dentist dentist = dentistRepository.findByIdAndActiveTrue(id);

        if (dentist == null) {

            return ResponseEntity.badRequest().body("Invalid information");

        } else {

            dentist.safeDelete();
            dentistRepository.save(dentist);
            return ResponseEntity.ok("Dentist deleted");
        }
    }

}
