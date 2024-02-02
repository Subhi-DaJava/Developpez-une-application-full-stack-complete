package com.openclassrooms.mddapi.dto;

import com.openclassrooms.mddapi.model.Topic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String username;
    private String email;
    private Set<TopicDto> topics;
}
