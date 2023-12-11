package br.rutiquewiski.HealthBridge.infra.security.controller;

import br.rutiquewiski.HealthBridge.infra.security.domain.DTO.UserRegistrationDTO;
import br.rutiquewiski.HealthBridge.infra.security.domain.UserAuth;
import br.rutiquewiski.HealthBridge.infra.security.repositories.UserAuthRepository;
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
@RequestMapping("/api/v1/user")
public class UserAuthController {

    @Autowired
    private UserAuthRepository userAuthRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserRegistrationDTO userRegistrationDTO, UriComponentsBuilder uriComponentsBuilder){

        var user = new UserAuth(userRegistrationDTO);

        userAuthRepository.save(user);

        var uri = uriComponentsBuilder.path("/api/v1/user/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.ok(uri);
    }
}
