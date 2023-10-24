package br.rutiquewiski.HealthBridge.controllers;

import br.rutiquewiski.HealthBridge.domain.professional.Dentist.Dentist;
import br.rutiquewiski.HealthBridge.domain.professional.Dentist.DentistListingDTO;
import br.rutiquewiski.HealthBridge.domain.professional.Dentist.DentistRegistrationDTO;
import br.rutiquewiski.HealthBridge.repositories.DentistRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/dentist")
public class DentistController {

    @Autowired
    DentistRepository dentistRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> registerDentist(@RequestBody @Valid DentistRegistrationDTO dentistRegistrationDTO, UriComponentsBuilder uriComponentsBuilder){

        var dentist = new Dentist(dentistRegistrationDTO);

        dentistRepository.save(dentist);

        var uri = uriComponentsBuilder.path("/dentist/{id}").buildAndExpand(dentist.getId()).toUri();

        return ResponseEntity.ok(uri);
    }

    @GetMapping
    public ResponseEntity<?> getAllDentists(Pageable pageable) {

        Page<DentistListingDTO> dentists = dentistRepository.findAllByActiveTrue(pageable).map(DentistListingDTO::new);

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

            return ResponseEntity.ok(dentist);
        }
    }


}
