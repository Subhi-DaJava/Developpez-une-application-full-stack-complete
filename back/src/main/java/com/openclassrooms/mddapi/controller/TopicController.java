package com.openclassrooms.mddapi.controller;

import java.util.List;

import com.openclassrooms.mddapi.dto.TopicDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.service.ITopicService;

@RestController
@RequestMapping("/topic")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class TopicController {
	private final ITopicService topicService;
	
	public TopicController(ITopicService topicService) {
		this.topicService = topicService;		
	}

	@GetMapping()
	public ResponseEntity<?> getAllTopics() {
		List<TopicDto> topics = topicService.getTopics();

		if (topics.isEmpty()) {
			log.error("No topics found in database");
			return ResponseEntity.badRequest().body("No topics found");
		}
		log.info("Topics successfully retrieved from database: {}", topics);
		return ResponseEntity.ok(topics);
	}

}
