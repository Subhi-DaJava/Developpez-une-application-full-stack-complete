package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("post")
@Slf4j
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

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
        List<PostDto> postDtoList = postService.getPostsFromSubscribedThemesChronologically(username);
        if(postDtoList == null) {
            log.error("Posts not found for user with username: {}", username);
            return ResponseEntity.notFound().build();
        }

        log.info("Posts successfully retrieved: {}", postDtoList);

        return ResponseEntity.ok(postDtoList);
    }

}
