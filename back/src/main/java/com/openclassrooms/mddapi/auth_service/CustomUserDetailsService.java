package com.openclassrooms.mddapi.auth_service;

import com.openclassrooms.mddapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        AuthUser user =
            userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .map(AuthUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username:{%s} ".formatted(usernameOrEmail)));

        log.info("User successfully loaded from database.");
        return user;
    }
}
