package com.openclassrooms.mddapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.openclassrooms.mddapi.model.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

}
