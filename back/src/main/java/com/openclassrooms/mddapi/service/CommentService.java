package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.CommentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class CommentService implements ICommentService {
    @Override
    public CommentDto createComment(CommentDto commentDto) {
        return null;
    }

    @Override
    public CommentDto getCommentById(Long commentId) {
        return null;
    }
}
