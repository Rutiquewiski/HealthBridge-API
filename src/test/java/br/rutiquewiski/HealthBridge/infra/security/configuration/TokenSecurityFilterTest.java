package br.rutiquewiski.HealthBridge.infra.security.configuration;

import br.rutiquewiski.HealthBridge.infra.security.domain.UserAuth;
import br.rutiquewiski.HealthBridge.infra.security.repositories.UserAuthRepository;
import br.rutiquewiski.HealthBridge.infra.security.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.context.SecurityContextHolder;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TokenSecurityFilterTest {

    @Mock
    private TokenService tokenService;

    @Mock
    private UserAuthRepository userAuthRepository;

    @InjectMocks
    private TokenSecurityFilter tokenSecurityFilter;

    @Test
    void doFilterInternal_ValidToken_SetAuthentication() throws ServletException, IOException {
        // Arrange
        MockitoAnnotations.initMocks(this);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        String token = "validToken";
        String username = "username";
        UserAuth user = new UserAuth();
        user.setUsername(username);

        when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
        when(tokenService.getSubject(token)).thenReturn(username);
        when(userAuthRepository.findByUsername(username)).thenReturn(user);

        // Act
        tokenSecurityFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(userAuthRepository).findByUsername(username);
        verify(filterChain).doFilter(any(), any());
        verify(tokenService).getSubject(token);
        assertEquals(user, SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    @Test
    void doFilterInternal_NoToken_NoAuthenticationSet() throws ServletException, IOException {
        // Arrange
        MockitoAnnotations.initMocks(this);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        when(request.getHeader("Authorization")).thenReturn(null);

        // Act
        tokenSecurityFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(userAuthRepository, never()).findByUsername(any());
        verify(filterChain).doFilter(any(), any());
    }


}

