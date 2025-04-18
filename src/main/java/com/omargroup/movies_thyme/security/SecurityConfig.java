package com.omargroup.movies_thyme.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAUthFilter;
    private final AuthenticationProvider authenticationProvider;
    public SecurityConfig(JwtAuthFilter jwtAUthFilter, AuthenticationProvider authenticationProvider) {
        this.jwtAUthFilter = jwtAUthFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
            httpSecurity.csrf()
                    .disable()
                    .authorizeHttpRequests()
                    .requestMatchers("/api/v1/auth/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authenticationProvider(authenticationProvider)
                    .addFilterBefore(jwtAUthFilter, UsernamePasswordAuthenticationFilter.class);


        return httpSecurity.build();
    }

}
