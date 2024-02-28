package com.food.Config;

import com.food.Filter.RequestFilter;
import com.food.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig
{
    private final UserRepository  userRepository;
    private final RequestFilter requestFilter;
    private final AuthenticationProvider    authenticationProvider  ;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(authorizeRequests ->
          authorizeRequests.requestMatchers("/api/v1/auth/**","/api/v1/image/**").permitAll()
            .anyRequest().authenticated()
        ).sessionManagement(sessionManagement ->
          sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).
              authenticationProvider(authenticationProvider).
              addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class);
       return http.build();
    }



}
