package com.GameBlackjack.blackjack;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .csrf().csrfTokenRepository(CookieServerCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/public/**").permitAll() // Permitir acceso público a ciertos endpoints
                        .anyExchange().authenticated()
                )
                .httpBasic(); // Permitir autenticación básica para pruebas

        return http.build();
    }
}
