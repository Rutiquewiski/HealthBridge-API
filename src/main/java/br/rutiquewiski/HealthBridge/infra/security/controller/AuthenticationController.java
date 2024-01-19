package br.rutiquewiski.HealthBridge.infra.security.controller;

import br.rutiquewiski.HealthBridge.infra.errors.LoginDataException;
import br.rutiquewiski.HealthBridge.infra.security.domain.DTO.AuthenticationDTO;
import br.rutiquewiski.HealthBridge.infra.security.domain.UserAuth;
import br.rutiquewiski.HealthBridge.infra.security.service.TokenService;
import br.rutiquewiski.HealthBridge.infra.security.token.ResponseTokenDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/key/login")
public class AuthenticationController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<ResponseTokenDTO> login(@RequestBody @Valid AuthenticationDTO authenticationDTO) throws LoginDataException {

        //Method responsible for login

        var AuthToken = new UsernamePasswordAuthenticationToken(authenticationDTO.username(), authenticationDTO.password());

        try {

            var authentication = authenticationManager.authenticate(AuthToken);

            var tokenJWT = tokenService.generateToken((UserAuth) authentication.getPrincipal());

            return ResponseEntity.ok(new ResponseTokenDTO(tokenJWT));

        } catch (Exception e) {

            throw new LoginDataException("Invalid username or password");
        }
    }
}
