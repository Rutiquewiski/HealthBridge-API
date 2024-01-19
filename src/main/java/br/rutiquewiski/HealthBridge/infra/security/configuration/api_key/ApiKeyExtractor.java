package br.rutiquewiski.HealthBridge.infra.security.configuration.api_key;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class ApiKeyExtractor {

    @Value("${api.security.key.name}")
    private String name;
    @Value("${api.security.key.value}")
    private String value;

    public Optional<Authentication> extract(HttpServletRequest request) {
        String providedKey = request.getHeader(name);
        if (providedKey == null || !providedKey.equals(value))
            return Optional.empty();

        return Optional.of(new ApiKeyAuth(providedKey, AuthorityUtils.NO_AUTHORITIES));
    }

}

