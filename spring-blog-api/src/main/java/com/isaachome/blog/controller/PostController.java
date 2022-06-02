package com.isaachome.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isaachome.blog.payload.PostDto;
import com.isaachome.blog.service.PostService;

/**
 *
 * @author IsaacHome
 */
@RestController
@RequestMapping("/api/posts")
public class PostController {

	@Autowired
	private PostService postService;
	
	// get all posts restApi
	@GetMapping
	public List<PostDto> getAllPosts(){
		return postService.getAllPosts();
	}
	
	// Create blog post
	@PostMapping
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
		return new ResponseEntity<PostDto>(postService.createPost(postDto),HttpStatus.CREATED);
	}
	
	
	
}
