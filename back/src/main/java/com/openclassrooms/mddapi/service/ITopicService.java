package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.TopicDto;

import java.util.List;

public interface ITopicService {

	List<TopicDto> getTopics();

	void subscribeToTopic(String topicName, String username);

	void unsubscribeToTopic(String topicName, String username);

}
