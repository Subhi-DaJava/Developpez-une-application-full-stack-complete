package com.openclassrooms.mddapi.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post implements Serializable {

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

	@Builder.Default
	@OneToMany(mappedBy = "post")
	private List<Comment> comments = new ArrayList<>();

	private String title;

	private String content;

	private LocalDate createdAt;
	
}
