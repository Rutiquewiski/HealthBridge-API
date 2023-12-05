package br.rutiquewiski.HealthBridge.infra.security.domain.DTO;

public record AuthenticationDTO(
        String username,
        String password
) {
}
