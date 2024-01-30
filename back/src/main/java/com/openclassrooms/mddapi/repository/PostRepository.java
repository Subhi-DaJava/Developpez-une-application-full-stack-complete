package com.openclassrooms.mddapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.openclassrooms.mddapi.model.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByTopicIdOrderByCreatedAtDesc(Long topicId);
}
