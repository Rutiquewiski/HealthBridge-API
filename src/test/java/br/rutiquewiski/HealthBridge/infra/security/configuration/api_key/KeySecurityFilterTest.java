package br.rutiquewiski.HealthBridge.infra.security.configuration.api_key;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KeySecurityFilterTest {

    @Mock
    private ApiKeyExtractor apiKeyExtractor;

    @InjectMocks
    private KeySecurityFilter keySecurityFilter;

    @Test
    void doFilterInternal_WithValidApiKey_SetsAuthentication() throws ServletException, IOException {
        // Arrange
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        Authentication authentication = createApiKeyAuth();
        when(apiKeyExtractor.extract(request)).thenReturn(Optional.of(authentication));

        // Act
        keySecurityFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(apiKeyExtractor).extract(request);
        verify(filterChain).doFilter(request, response);
        assertSame(authentication, SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void doFilterInternal_WithInvalidApiKey_NoAuthenticationSet() throws ServletException, IOException {
        // Arrange
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        when(apiKeyExtractor.extract(request)).thenReturn(Optional.empty());

        // Act
        keySecurityFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(apiKeyExtractor).extract(request);
        verify(filterChain).doFilter(request, response);
        assertNull(SecurityContextHolder.getContext().getAuthentication().getDetails());
    }

    private Authentication createApiKeyAuth() {
        Collection<? extends GrantedAuthority> authorities = Collections.emptyList();
        return new AbstractAuthenticationToken(authorities) {
            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return "testKey";
            }
        };
    }
}


