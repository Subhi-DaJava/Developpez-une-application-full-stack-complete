package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.exception.FieldShouldNotBeEmptyException;
import com.openclassrooms.mddapi.exception.ResourceNotFoundException;
import com.openclassrooms.mddapi.mapper.PostMapper;
import com.openclassrooms.mddapi.model.Post;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.PostRepository;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@Slf4j
public class PostService implements IPostService {
	private final TopicRepository topicRepository;
	private final PostRepository postRepository;
	private final UserRepository userRepository;
	private final PostMapper postMapper;

	public PostService(PostRepository postRepository, UserRepository userRepository, PostMapper postMapper,
					   TopicRepository topicRepository) {
		this.postRepository = postRepository;
		this.userRepository = userRepository;
		this.postMapper = postMapper;
		this.topicRepository = topicRepository;
	}

	@Override
	public PostDto createPost(PostDto postDto) {
		if(postDto.getAuthorUsername() == null ||
				postDto.getAuthorUsername().isEmpty() ||
				postDto.getTopicName() == null ||
				postDto.getTopicName().isEmpty() ||
				postDto.getTitle() == null ||
				postDto.getTitle().isEmpty() ||
				postDto.getContent() == null ||
				postDto.getContent().isEmpty()) {
			throw new FieldShouldNotBeEmptyException("Field should not be empty");
		}

		User user = getUserByUsername(postDto.getAuthorUsername());

		Topic topic = retrieveTopicByTopicName(postDto.getTopicName());

		Post post = Post.builder()
				.createdAt(LocalDate.now())
				.title(postDto.getTitle())
				.content(postDto.getContent())
				.user(user)
				.topic(topic)
				.build();

		Post savedPost = postRepository.save(post);

		PostDto savedPostDto = postMapper.postToPostDto(savedPost);
		log.info("Post successfully created: {}", savedPostDto);
		return savedPostDto;
	}

	private User getUserByUsername(String authorUsername) {
		return userRepository.findByUsername(authorUsername).orElseThrow(() -> new ResourceNotFoundException("User not found with username:{%s} ".formatted(authorUsername)));
	}

	private Topic retrieveTopicByTopicName(String topicName) {
		return topicRepository.findByName(topicName).orElseThrow(() -> new ResourceNotFoundException("Topic not found with name:{%s} ".formatted(topicName)));
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
