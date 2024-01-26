package com.openclassrooms.mddapi.service;

import java.util.List;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.mapper.TopicMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.repository.TopicRepository;

@Service
@Slf4j
public class TopicService implements ITopicService {

	private final TopicRepository topicRepository;
	private final TopicMapper topicMapper;
	
	public TopicService(TopicRepository topicRepository, TopicMapper topicMapper) {
		this.topicRepository = topicRepository;
        this.topicMapper = topicMapper;
    }

	@Override
	public List<TopicDto> getTopics() {

		List<TopicDto> topicDtoList;
		List<Topic> topics = topicRepository.findAll();

		topicDtoList = this.topicMapper.topicsToTopicDtos(topics);
		log.info("Topics successfully retrieved from database: {}", topicDtoList);

		return topicDtoList;
	}
	
}
