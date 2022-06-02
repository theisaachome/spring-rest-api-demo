package com.isaachome.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	

	// Create blog post
	@PostMapping
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
		return new ResponseEntity<PostDto>(postService.createPost(postDto),HttpStatus.CREATED);
	}
	
	
	// get all posts restApi
	@GetMapping
	public List<PostDto> getAllPosts(){
		return postService.getAllPosts();
	}
	//  get post by id restApi
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable(name="id") long id){
		return  ResponseEntity.ok(postService.getPostById(id));
	}
	
	// update post by id restApi
	@PutMapping("/{id}")
	public ResponseEntity<PostDto> updatePostById(@RequestBody PostDto postDto,
			@PathVariable(name="id")Long id){
		PostDto postResponse= postService.updatePost(postDto, id);
		return new ResponseEntity<>(postResponse,HttpStatus.OK);
		
	}
	// delete post by id restApi
	@DeleteMapping("/{id}")
	public ResponseEntity<String>deletePostById (@PathVariable(name="id")long id) {
		postService.deletePostById(id);
		return new ResponseEntity<String>("Post entity deleted successfully",HttpStatus.OK);
	}
	
}
