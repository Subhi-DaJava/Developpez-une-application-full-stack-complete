package com.openclassrooms.mddapi.service;

import java.util.List;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.exception.FieldShouldNotBeEmptyException;
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
	public void subscribeToTopic(String topicName, String username) {
		if(topicName == null || topicName.isEmpty() || username == null || username.isEmpty()) {
			throw new FieldShouldNotBeEmptyException("Field should not be empty");
		}
		User user = retrieveUserByUsername(username);
		Topic topic = getTopicByTopicName(topicName);

		if (!user.getTopics().contains(topic)) {
			user.getTopics().add(topic);
			log.info("User with username {} successfully subscribed to topic with name: {}", username, topicName);
			userRepository.save(user);
		} else {
			log.info("User with username {} is already subscribed to topic with name: {}", username, topicName);
		}

	}

	@Override
	public void unsubscribeToTopic(String topicName, String username) {
		if(topicName == null || topicName.isEmpty() || username == null || username.isEmpty()) {
			throw new FieldShouldNotBeEmptyException("Field should not be empty");
		}

		User user = retrieveUserByUsername(username);
		Topic topic = getTopicByTopicName(topicName);

		if (user.getTopics().contains(topic)) {
			user.getTopics().remove(topic);
			log.info("User with username {} successfully unsubscribed to topic with name: {}", username, topicName);
			userRepository.save(user);
		} else {
			log.info("User with username {} is not subscribed to topic with name: {}", username, topicName);
		}

	}

	private Topic getTopicByTopicName(String topicName) {
		return topicRepository.findByName(topicName).orElseThrow(() -> new ResourceNotFoundException("Topic not found with name:{%s} ".formatted(topicName)));
	}

	private User retrieveUserByUsername(String username) {
		return userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found with username:{%s} ".formatted(username)));
	}

}
