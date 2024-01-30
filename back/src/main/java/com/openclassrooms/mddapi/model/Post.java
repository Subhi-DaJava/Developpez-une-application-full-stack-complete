package com.openclassrooms.mddapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"user", "topic", "comments"})
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="post_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "topic_id")
	private Topic topic;

	@OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
	private List<Comment> comments = new ArrayList<>();

	private String title;

	private String content;

	private LocalDate createdAt;
	
}
