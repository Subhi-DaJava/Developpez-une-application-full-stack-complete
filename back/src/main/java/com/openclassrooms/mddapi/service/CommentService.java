package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.exception.FieldShouldNotBeEmptyException;
import com.openclassrooms.mddapi.exception.ResourceNotFoundException;
import com.openclassrooms.mddapi.mapper.CommentMapper;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.CommentRepository;
import com.openclassrooms.mddapi.repository.PostRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class CommentService implements ICommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentMapper commentMapper;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository, PostRepository postRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto) {
        if(commentDto.getContent() == null ||
                commentDto.getContent().isEmpty() ||
                commentDto.getPostId() == null ||
                commentDto.getPostId() == 0L ||
                commentDto.getAuthorUsername() == null ||
                commentDto.getAuthorUsername().isEmpty()) {
            throw new FieldShouldNotBeEmptyException("CommentDto, username and postId must not be null or empty");
        }
        User user = getUserByUsername(commentDto.getAuthorUsername());

        Comment comment = Comment.builder()
                .content(commentDto.getContent())
                .user(user)
                .post(getPostById(commentDto.getPostId()))
                .build();

        comment = commentRepository.save(comment);

        CommentDto commentDtoSaved = commentMapper.commentToCommentDto(comment);

        log.info("Comment successfully created: {}", commentDtoSaved);

        return commentDtoSaved;
    }

    private User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User with username:{%s} not found".formatted(username)));
    }

    private Post getPostById(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post with id:{%s} not found".formatted(postId)));
    }
}
