package com.isaachome.blog.entity;

import javax.persistence.*;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
	name = "posts",
	uniqueConstraints={@UniqueConstraint(columnNames={"title"})}
)
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="title",nullable = false)
	private String title;
	@Column(name="description",nullable = false)
	private String description;
	@Column(name="content",nullable = false)
	private String content;

	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Comment> comments = new ArrayList<>();
	
}
