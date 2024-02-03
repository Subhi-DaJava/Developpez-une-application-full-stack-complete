package com.openclassrooms.mddapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
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
	@Serial
	private static final long serialVersionUID = 1905122041950251207L;
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
