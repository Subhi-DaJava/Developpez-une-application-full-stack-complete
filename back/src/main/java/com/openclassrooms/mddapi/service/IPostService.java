package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.dto.PostDtoLight;

import java.util.List;

public interface IPostService {

    PostDto createPost(PostDto postDto);

    /**
     * Get all posts subscribed themes by user
     * @param username String
     * @return List of postDtoLight(posts) chronologically ordered by date for the user subscribed themes
     */
    List<PostDtoLight> getPostsFromSubscribedThemesChronologically(String username);

    PostDto getPostById(Long postId);
}
