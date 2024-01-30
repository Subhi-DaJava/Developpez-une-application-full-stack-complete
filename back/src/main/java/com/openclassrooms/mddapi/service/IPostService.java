package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.PostDto;

import java.util.List;

public interface IPostService {

    PostDto createPost(PostDto postDto);
    List<PostDto> getPostsFromSubscribedThemesChronologically(String username);

    PostDto getPostById(Long postId);
}
