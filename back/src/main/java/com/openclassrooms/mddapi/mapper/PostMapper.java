package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.dto.PostDtoLight;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(source ="user.username" , target = "authorUsername")
    @Mapping(source = "post.id", target = "postId")
    CommentDto commentToCommentDto(Comment comment);

    @Mapping(source = "topic.name", target = "topicName")
    @Mapping(source = "user.username", target = "authorUsername")
    @Mapping(target = "comments", expression = "java(post.getComments() != null ? commentsToCommentDtos(post.getComments()) : new ArrayList<>())")
    PostDto postToPostDto(Post post);

    List<PostDto> postsToPostDtos(List<Post> posts);

    default List<CommentDto> commentsToCommentDtos(List<Comment> comments) {
        return comments.stream().map(this::commentToCommentDto).toList();
    }

    @Mapping(source = "topic.name", target = "topicName")
    @Mapping(source = "user.username", target = "authorUsername")
    PostDtoLight postToPostDtoLight(Post post);

    List<PostDtoLight> postsToPostDtoLight(List<Post> posts);

}
