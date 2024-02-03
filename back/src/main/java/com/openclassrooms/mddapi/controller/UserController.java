package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.UserRequest;
import com.openclassrooms.mddapi.dto.UserResponse;
import com.openclassrooms.mddapi.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{username}")
    ResponseEntity<?> getUserById(@PathVariable(name = "username") String username) {
        UserResponse userResponse = userService.getUserByUserName(username);
        if(userResponse == null || userResponse.getUsername() == null) {
            log.error("User not found in database");
            return ResponseEntity.badRequest().body("User not found with username:{%s}".formatted(username));
        }
        log.info("User successfully retrieved from database: {}", userResponse);
        return ResponseEntity.ok(userResponse);
    }

    @PutMapping("/{username}")
    ResponseEntity<?> updateUser(@PathVariable(name = "username") String username, @RequestBody UserRequest userRequest) {
        userService.updateUser(username, userRequest);
        log.info("User successfully updated in database");
        return ResponseEntity.ok().build();
    }

}
