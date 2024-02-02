package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.dto.PostDtoLight;

import java.util.List;

public interface IPostService {

    PostDto createPost(PostDto postDto);
    List<PostDtoLight> getPostsFromSubscribedThemesChronologically(String username);

    PostDto getPostById(Long postId);
}
