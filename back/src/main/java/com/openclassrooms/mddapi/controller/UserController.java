package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.UserRequest;
import com.openclassrooms.mddapi.dto.UserResponse;
import com.openclassrooms.mddapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getUserById(@PathVariable(name = "id") Long userId) {
        UserResponse userResponse = userService.getUserById(userId);
        if(userResponse == null || userResponse.getId() == null) {
            log.error("User not found in database");
            return ResponseEntity.badRequest().body("User not found");
        }
        log.info("User successfully retrieved from database: {}", userResponse);
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping
    ResponseEntity<?> addUser(@RequestBody UserRequest userResponse) {
        UserResponse userCreated = userService.addUser(userResponse);

        if(userCreated == null || userCreated.getId() == null) {
            log.error("User not added to database");
            return ResponseEntity.badRequest().body("User not added");
        }
        log.info("User successfully added to database: {}", userCreated);
        return ResponseEntity.ok(userCreated);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateUser(@PathVariable(name = "id") Long userId, @RequestBody UserRequest userRequest) {
        userService.updateUser(userId, userRequest);
        log.info("User successfully updated in database");
        return ResponseEntity.ok().build();
    }

}
