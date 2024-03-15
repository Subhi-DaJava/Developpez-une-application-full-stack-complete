package com.openclassrooms.mddapi.security_config;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.openclassrooms.mddapi.auth_service.CustomUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.crypto.spec.SecretKeySpec;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@Slf4j
public class SpringSecurityConfig {

    @Value("${jwtKey}")
    private String jwtKey;

    private final CustomUserDetailsService userDetailsService;

    public SpringSecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * This method is used to create an AuthenticationManager bean.
     * @return an AuthenticationManager bean
     */
    @Bean
    public AuthenticationManager authenticationManager() {
        var authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        log.info("AuthenticationManager bean is successfully created.");
        return new ProviderManager(authProvider);
    }

    /**
     * This method is used to create a SecurityFilterChain bean.
     * @param http the HttpSecurity object
     * @return a SecurityFilterChain bean
     * @throws Exception if an error occurs
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return
                http
                        .csrf(AbstractHttpConfigurer::disable)
                        .cors(Customizer.withDefaults())
                        .authorizeHttpRequests(auth ->
                                auth
                                        .requestMatchers("/auth/**").permitAll()
                                        .anyRequest().authenticated()
                        )
                        .sessionManagement(sessionManagement ->
                                sessionManagement
                                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .oauth2ResourceServer(oauth2 ->
                                oauth2
                                        .jwt(jwt -> jwt.decoder(jwtDecoder())))
                        .userDetailsService(userDetailsService)
                        .httpBasic(Customizer.withDefaults())
                        .build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        SecretKeySpec secretKe = new SecretKeySpec(this.jwtKey.getBytes(), 0, this.jwtKey.getBytes().length, "RSA");

        return NimbusJwtDecoder.withSecretKey(secretKe).macAlgorithm(MacAlgorithm.HS256).build();
    }

    @Bean
    public JwtEncoder jwtEncoder() {
        return new NimbusJwtEncoder(new ImmutableSecret<>(this.jwtKey.getBytes()));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        log.info("PasswordEncoder bean is successfully created.");
        return new BCryptPasswordEncoder();
    }

    /**
     * This method is used to create a CorsConfigurationSource bean.
     * It allows configuring CORS.
     * CORS is a security feature that restricts what resources a web page can request from another domain.
     * @return a CorsConfigurationSource bean
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        return request -> {
            var cors = new CorsConfiguration();
            cors.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
            cors.setAllowedMethods(Collections.singletonList("*"));
            cors.setAllowedHeaders(Collections.singletonList("*"));
            return cors;
        };
    }
}
