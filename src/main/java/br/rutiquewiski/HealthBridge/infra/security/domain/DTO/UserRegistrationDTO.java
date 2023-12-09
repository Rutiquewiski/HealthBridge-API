package br.rutiquewiski.HealthBridge.infra.security.domain.DTO;

import jakarta.validation.constraints.NotNull;

public record UserRegistrationDTO(
        @NotNull
        String name,
        @NotNull
        String username,
        @NotNull
        String password
) {
}
