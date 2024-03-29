package com.openclassrooms.mddapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.openclassrooms.mddapi.model.Post;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    /**
     * This method retrieves a list of Post entities from the database associated with a specific Topic,
     * ordered by their creation date in descending order.
     * It uses the Spring Data JPA query method convention to generate the query.
     * The method name 'findAllByTopicIdOrderByCreatedAtDesc' is interpreted as follows:
     * - 'findAll': fetch multiple entities.
     * - 'ByTopicId': where the 'topicId' attribute matches the provided parameter.
     * - 'OrderByCreatedAtDesc': sort the results by the 'createdAt' attribute in descending order.
     *
     * @param topicId the ID of the Topic entity for which the posts are to be retrieved
     * @return a List<Post> containing the Post entities associated with the specified Topic,
     * ordered by their creation date in descending order
     */
    List<Post> findAllByTopicIdOrderByCreatedAtDesc(Long topicId);
}
