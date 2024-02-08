package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.dto.PostDtoLight;
import com.openclassrooms.mddapi.service.PostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("post")
@AllArgsConstructor
@Slf4j
public class PostController {
    private final PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable Long id) {
        PostDto postDto = postService.getPostById(id);
        if(postDto == null) {
            log.error("Post not found with id: {}", id);
            return ResponseEntity.notFound().build();
        }

        log.info("Post successfully retrieved: {}", postDto);
        return ResponseEntity.ok(postDto);
    }

    @GetMapping
    public ResponseEntity<?> getAllPostsSubscribedThemesChronologicallyByUser(@RequestParam(name = "username") String username) {
        List<PostDtoLight> postDtoList = postService.getPostsFromSubscribedThemesChronologically(username);
        if(postDtoList == null) {
            log.error("No posts found for the topics to which a user with the username {} is subscribed", username);
            return ResponseEntity.notFound().build();
        }

        if(postDtoList.isEmpty()) {
            String errorMessage = "User with username: %s doesn't subscribe any topic: ".formatted(username);
            log.info(errorMessage);
           return ResponseEntity.noContent().build();
        }

        log.info("Posts successfully retrieved: {}", postDtoList);

        return ResponseEntity.ok(postDtoList);
    }

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostDto postDto) {
        PostDto savedPostDto = postService.createPost(postDto);
        if(savedPostDto == null) {
            log.error("Post not created");
            return ResponseEntity.badRequest().build();
        }

        log.info("Post successfully created: {}", savedPostDto);
        return ResponseEntity.ok(savedPostDto);
    }

}
