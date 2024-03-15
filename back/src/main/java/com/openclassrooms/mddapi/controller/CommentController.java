package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.service.ICommentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@AllArgsConstructor
@Slf4j
public class CommentController {
    private final ICommentService commentService;

    /**
     * Create a comment
     * @param commentDto CommentDto
     * @return ResponseEntity with the created comment(commentDto) and status code 201
     */
    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto) {
        CommentDto commentDtoSaved = commentService.createComment(commentDto);
        log.info("Comment successfully created: {}", commentDtoSaved);
        return new ResponseEntity<>(commentDtoSaved, HttpStatus.CREATED);
    }
}
