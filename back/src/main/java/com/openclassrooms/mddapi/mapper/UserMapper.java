package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.dto.UserRequest;
import com.openclassrooms.mddapi.dto.UserResponse;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    TopicDto topicToTopicDto(Topic topic);

    @Mapping(target = "topics", expression = "java(mapTopics(user.getTopics()))")
    UserResponse userToUserResponse(User user);

    User userRequestToUser(UserRequest userRequest);

    /**
     * Map a set of topics to a set of topicDto: if the set is null, return an empty set, or map the topics
     * @param topics the set of topics to map
     * @return the set of topicDto
     */
    default Set<TopicDto> mapTopics(Set<Topic> topics) {
        return topics != null ? topics.stream().map(this::topicToTopicDto).collect(Collectors.toSet()) : new HashSet<>();
    }
}
