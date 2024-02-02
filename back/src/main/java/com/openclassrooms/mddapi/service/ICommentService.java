package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.CommentDto;

public interface ICommentService {

    CommentDto createComment(CommentDto commentDto);
}
