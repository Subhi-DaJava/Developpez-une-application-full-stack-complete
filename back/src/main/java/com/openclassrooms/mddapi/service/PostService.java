package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.exception.ResourceNotFoundException;
import com.openclassrooms.mddapi.mapper.PostMapper;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.PostRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
@Slf4j
public class PostService implements IPostService {

	private final PostRepository postRepository;
	private final UserRepository userRepository;
	private final PostMapper postMapper;

	public PostService(PostRepository postRepository, UserRepository userRepository, PostMapper postMapper) {
		this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.postMapper = postMapper;
    }

	@Override
	public PostDto createPost(PostDto postDto) {
		Post post = postMapper.postDtoToPost(postDto);
		Post savedPost = postRepository.save(post);
		PostDto savedPostDto = postMapper.postToPostDto(savedPost);
		log.info("Post successfully created: {}", savedPostDto);
		return savedPostDto;
	}

	@Override
	public List<PostDto> getPostsFromSubscribedThemesChronologically(String username) {
		User user = retrieveUserByUsername(username);

		Set<Topic> topics = user.getTopics();
		// Retrieve all posts from subscribed topics
		// Retrieve posts associated with the topics to which a user has subscribed
		List<Post> postList = topics.stream()
				.map(this::getAllByTopicIdOrderByCreatedAtDesc)
				.flatMap(List::stream)
				.toList();

		List<PostDto> postDtoList = this.postMapper.postsToPostDtos(postList);

		log.info("Posts successfully retrieved from database: {}", postDtoList);

		return postDtoList;
	}

	private List<Post> getAllByTopicIdOrderByCreatedAtDesc(Topic topic) {
		return postRepository.findAllByTopicIdOrderByCreatedAtDesc(topic.getId());
	}

	private User retrieveUserByUsername(String username) {
		// Retrieve user from database with topics
	 return userRepository.findWithTopicsByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found with username:{%s} ".formatted(username)));

	}

	@Override
	public PostDto getPostById(Long postId) {

		Post post = retrievePostById(postId);

		PostDto postDto = this.postMapper.postToPostDto(post);

		log.info("Post successfully retrieved from database: {}", postDto);

		return postDto;
	}

	private Post retrievePostById(Long postId) {
		return postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not found with id:{%d} ".formatted(postId)));
	}
}
