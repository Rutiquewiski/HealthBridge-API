package br.rutiquewiski.HealthBridge.infra.security.configuration;

import br.rutiquewiski.HealthBridge.infra.security.repositories.UserAuthRepository;
import br.rutiquewiski.HealthBridge.infra.security.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class TokenSecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserAuthRepository userAuthRepository;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var tokenJWKT = recuperarToken(request);

        if (tokenJWKT != null) {

            var subject = tokenService.getSubject(tokenJWKT);
            var user = userAuthRepository.findByUsername(subject);

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {

        var authHeader = request.getHeader("Authorization");

        if(authHeader != null) {
            return authHeader.replace("Bearer", "").trim();
        }

        return null;
    }
}
