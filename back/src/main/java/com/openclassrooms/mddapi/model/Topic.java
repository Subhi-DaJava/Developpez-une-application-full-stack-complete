package com.openclassrooms.mddapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "topics")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Topic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "topic_id")
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String name;

	@Column(nullable = false)
	private String description;

	@OneToMany(mappedBy = "topic")
	private List<Post> posts = new ArrayList<>();
}
