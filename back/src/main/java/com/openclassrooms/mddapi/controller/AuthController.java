package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.auth_service.AuthService;
import com.openclassrooms.mddapi.auth_service.AuthUser;
import com.openclassrooms.mddapi.dto.AuthRequest;
import com.openclassrooms.mddapi.dto.JwtToken;
import com.openclassrooms.mddapi.dto.UserRequest;
import com.openclassrooms.mddapi.dto.UserResponse;
import com.openclassrooms.mddapi.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        authRequest.getUsernameOrEmail(),
                        authRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        AuthUser userDetails = (AuthUser) authentication.getPrincipal();

        String username = userDetails.getUsername();

        String token = authService.generateToken(authentication);
        JwtToken jwtToken = new JwtToken("Token Token successfully generated", username, token);

        log.info("Token successfully generated: {}, username: {} ", jwtToken.token(), username);
        return ResponseEntity.ok(jwtToken);
    }

    @PostMapping("/register")
    ResponseEntity<?> register(@RequestBody UserRequest userResponse) {
        UserResponse userCreated = userService.addUser(userResponse);

        if(userCreated == null || userCreated.getUsername() == null){
            log.error("User not added to database");
            return ResponseEntity.badRequest().body("User not added");
        }
        log.info("User successfully added to database: {}", userCreated);
        return ResponseEntity.ok(userCreated);
    }

}
