package br.rutiquewiski.HealthBridge.infra.security.controller;

import br.rutiquewiski.HealthBridge.infra.security.domain.DTO.UserRegistrationDTO;
import br.rutiquewiski.HealthBridge.infra.security.domain.DTO.UserUpdateDTO;
import br.rutiquewiski.HealthBridge.infra.security.domain.UserAuth;
import br.rutiquewiski.HealthBridge.infra.security.repositories.UserAuthRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api")
public class UserAuthController {

    @Autowired
    private UserAuthRepository userAuthRepository;

    @SecurityRequirement(name = "api-key")
    @PostMapping("/key/user")
    @Transactional
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserRegistrationDTO userRegistrationDTO, UriComponentsBuilder uriComponentsBuilder){

        UserAuth user = new UserAuth(userRegistrationDTO);

        userAuthRepository.save(user);

        var uri = uriComponentsBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.ok(uri);
    }

    @PatchMapping("/user/{id}")
    @Transactional
    public ResponseEntity<?> updateUser(@RequestBody UserUpdateDTO userUpdateDTO, @PathVariable Integer id){

        UserAuth user = userAuthRepository.findByIdAndActiveTrue(id);

        if (user == null){

            return ResponseEntity.badRequest().body("Invalid id");

        } else {

            user.updateInfo(userUpdateDTO);
            userAuthRepository.save(user);

            return ResponseEntity.ok("User information updated");
        }
    }

    @DeleteMapping("/user/{id}")
    @Transactional
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){

        UserAuth user = userAuthRepository.findByIdAndActiveTrue(id);

        if (user == null){

            return ResponseEntity.badRequest().body("Invalid id");

        } else {

            user.safeDelete();
            userAuthRepository.save(user);

            return ResponseEntity.ok("User deleted");
        }
    }
}
