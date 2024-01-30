package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.dto.PostDto;
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
    @Mapping(source ="comments", target = "comments")
    PostDto postToPostDto(Post post);

    @Mapping(source = "topicName", target = "topic.name")
    @Mapping(source = "authorUsername", target = "user.username")
    @Mapping(source = "comments", target = "comments")
    Post postDtoToPost(PostDto postDto);

    List<PostDto> postsToPostDtos(List<Post> posts);

}
