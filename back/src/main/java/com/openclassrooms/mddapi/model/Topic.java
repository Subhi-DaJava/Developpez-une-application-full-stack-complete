package com.openclassrooms.mddapi.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "topics")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Topic implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "topic_id")
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String name;

	@Column(nullable = false)
	private String description;

	@Builder.Default
	@OneToMany(mappedBy = "topic")
	private List<Post> posts = new ArrayList<>();
}
