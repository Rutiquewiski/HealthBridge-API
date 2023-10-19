package br.rutiquewiski.HealthBridge.controllers;

import br.rutiquewiski.HealthBridge.domain.professional.Dentist.Dentist;
import br.rutiquewiski.HealthBridge.domain.professional.Dentist.DentistRegistrationDTO;
import br.rutiquewiski.HealthBridge.repositories.DentistRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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
}
