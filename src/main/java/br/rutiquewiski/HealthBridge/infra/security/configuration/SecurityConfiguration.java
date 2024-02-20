package br.rutiquewiski.HealthBridge.infra.security.configuration;

import br.rutiquewiski.HealthBridge.infra.security.configuration.api_key.KeySecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Configuration
    @EnableWebSecurity
    @Order(1)
    public static class ApiKeySecurityConfiguration {

        //Api key auth, any endpoint with key in the url uses authentication with api key instead of the jwt token

        @Autowired
        private KeySecurityFilter keySecurityFilter;

        @Bean
        public SecurityFilterChain apiKeySecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
            return httpSecurity
                    .csrf(AbstractHttpConfigurer::disable)
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authorizeHttpRequests(auth -> {
                                auth.requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll();
                                auth.requestMatchers( AntPathRequestMatcher.antMatcher("/api/**") ).authenticated(); //Change to /api/key/**
                            }
                    )
                    .addFilterBefore(keySecurityFilter, UsernamePasswordAuthenticationFilter.class)
                    .build();
        }

        @Bean
        public AuthenticationManager authenticationManager2(AuthenticationConfiguration authenticationConfiguration) throws Exception {
            return  authenticationConfiguration.getAuthenticationManager();
        }

    }

//    @EnableWebSecurity
//    @Configuration
//    @Order(2)  //This annotation sets this configuration to be the second one in the auth order
//    public static class StandardSecurityConfiguration {
//
//        //Regular token auth
//
//        @Autowired
//        private TokenSecurityFilter tokenSecurityFilter;
//
//        @Bean
//        public SecurityFilterChain tokenSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
//            return httpSecurity.csrf(AbstractHttpConfigurer::disable)
//                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                    .authorizeHttpRequests(auth -> {
//                        auth.requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll();
//                        auth.anyRequest().authenticated();
//                    })
//                    .addFilterBefore(tokenSecurityFilter, UsernamePasswordAuthenticationFilter.class)
//                    .build();
//        }
//
//        @Bean
//        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//            return  authenticationConfiguration.getAuthenticationManager();
//        }
//
//        @Bean
//        public PasswordEncoder passwordEncoder() {
//            return new BCryptPasswordEncoder();
//        }
//
//    }

}

//TODO: FIX THE AUTH, CURRENTLY SPRING BOOT IS NOT ACCEPTING TWO DIFFERENT AUTHENTICATION METHODS