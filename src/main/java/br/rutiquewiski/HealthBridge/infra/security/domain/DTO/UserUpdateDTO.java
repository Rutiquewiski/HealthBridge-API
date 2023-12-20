package br.rutiquewiski.HealthBridge.infra.security.domain.DTO;

import jakarta.validation.constraints.NotNull;

public record UserUpdateDTO(

        String name,
        String username
) {
}
