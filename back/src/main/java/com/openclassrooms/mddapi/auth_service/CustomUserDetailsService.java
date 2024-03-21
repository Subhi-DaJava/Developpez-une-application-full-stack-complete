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

    /**
     * Load user data from the database.
     * @param usernameOrEmail the usernameOrEmail identifying the user whose data is required.
     * @return UserDetails object containing the user data.
     * @throws UsernameNotFoundException if the user is not found in the database.
     */
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        return userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .map(AuthUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username:{%s} ".formatted(usernameOrEmail)));
    }
}
