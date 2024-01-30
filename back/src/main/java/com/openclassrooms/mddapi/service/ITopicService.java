package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.TopicDto;

import java.util.List;

public interface ITopicService {

	List<TopicDto> getTopics();

	void subscribeToTopic(Long topicId, Long userId);

	void unsubscribeToTopic(Long topicId, Long userId);

}
