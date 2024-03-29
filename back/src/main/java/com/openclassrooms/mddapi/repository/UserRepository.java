package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    /**
     * This method retrieves a User entity from the database by its username along with its associated topics.
     * It uses the @EntityGraph annotation to specify that the 'topics' relationship should be eagerly fetched.
     *
     * @EntityGraph is a JPA annotation that allows defining which attributes to fetch eagerly in a single query.
     * In this case, it fetches the 'topics' relationship of the User entity in the same query, thus improving performance.
     *
     * @param username the username of the User entity to be retrieved
     * @return an Optional<User> that contains the User entity if found, or an empty Optional if not found
     */
    @EntityGraph(attributePaths = "topics")
    Optional<User> findWithTopicsByUsername(String username);

    Optional<User> findByUsernameOrEmail(String username,String email);
}
