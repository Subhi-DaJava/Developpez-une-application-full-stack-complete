package com.openclassrooms.mddapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDtoLight {
    private Long id;
    private String title;
    private String content;
    private LocalDate createdAt;
    private String authorUsername;
    private String topicName;
}
