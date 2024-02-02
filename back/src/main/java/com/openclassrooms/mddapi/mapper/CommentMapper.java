package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(source = "post.id", target = "postId")
    @Mapping(source = "user.username", target = "authorUsername")
    CommentDto commentToCommentDto(Comment comment);

}
