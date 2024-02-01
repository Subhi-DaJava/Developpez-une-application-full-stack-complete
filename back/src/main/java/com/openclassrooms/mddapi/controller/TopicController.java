package com.openclassrooms.mddapi.controller;

import java.util.List;

import com.openclassrooms.mddapi.dto.TopicDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	@PostMapping("/subscribe")
	public ResponseEntity<?> subscribeToTopic(@RequestParam(name = "topicName") String topicName, @RequestParam(name = "username") String username) {
		topicService.subscribeToTopic(topicName, username);
		log.info("User with username: {} successfully subscribed to topic with name: {}", username, topicName);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/unsubscribe")
	public ResponseEntity<?> unsubscribeToTopic(@RequestParam(name = "topicName") String topicName, @RequestParam(name = "username") String username) {
		topicService.unsubscribeToTopic(topicName, username);
		log.info("User with username: {} successfully unsubscribed to topic with name: {}", username, topicName);
		return ResponseEntity.ok().build();
	}

}
