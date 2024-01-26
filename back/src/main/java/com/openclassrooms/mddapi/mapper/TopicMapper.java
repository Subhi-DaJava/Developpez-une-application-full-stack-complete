package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.model.Topic;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TopicMapper {

    TopicDto topicToTopicDto(Topic topic);

    Topic topicDtoToTopic(TopicDto topicDto);

    List<Topic> topicDtosToTopics(List<TopicDto> topicDtos);
    List<TopicDto> topicsToTopicDtos(List<Topic> topics);
}
