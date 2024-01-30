package com.openclassrooms.mddapi.service;

import java.util.List;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.exception.ResourceNotFoundException;
import com.openclassrooms.mddapi.mapper.TopicMapper;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.repository.TopicRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class TopicService implements ITopicService {

	private final TopicRepository topicRepository;
	private final TopicMapper topicMapper;
	private final UserRepository userRepository;

	public TopicService(
			TopicRepository topicRepository,
			TopicMapper topicMapper,
			UserRepository userRepository) {
		this.topicRepository = topicRepository;
		this.topicMapper = topicMapper;
		this.userRepository = userRepository;
	}

	@Override
	public List<TopicDto> getTopics() {

		List<TopicDto> topicDtoList;
		List<Topic> topics = topicRepository.findAll();

		topicDtoList = this.topicMapper.topicsToTopicDtos(topics);
		log.info("Topics successfully retrieved from database: {}", topicDtoList);

		return topicDtoList;
	}

	@Override
	public void subscribeToTopic(Long topicId, Long userId) {
		User user = retrieveUserById(userId);
		Topic topic = getTopicById(topicId);

		if (!user.getTopics().contains(topic)) {
			user.getTopics().add(topic);
			log.info("User with id: {} successfully subscribed to topic with id: {}", userId, topicId);
			userRepository.save(user);
		} else {
			log.info("User with id: {} is already subscribed to topic with id: {}", userId, topicId);
		}

	}

	@Override
	public void unsubscribeToTopic(Long topicId, Long userId) {

		User user = retrieveUserById(userId);
		Topic topic = getTopicById(topicId);

		if (user.getTopics().contains(topic)) {
			user.getTopics().remove(topic);
			log.info("User with id: {} successfully unsubscribed to topic with id: {}", userId, topicId);
			userRepository.save(user);
		} else {
			log.info("User with id: {} is not subscribed to topic with id: {}", userId, topicId);
		}

	}

	private Topic getTopicById(Long topicId) {
		return topicRepository.findById(topicId).orElseThrow(() -> new ResourceNotFoundException("Topic not found for this id:{%d}".formatted(topicId)));
	}

	private User retrieveUserById(Long userId) {
		return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for this id:{%d}".formatted(userId)));
	}

}
