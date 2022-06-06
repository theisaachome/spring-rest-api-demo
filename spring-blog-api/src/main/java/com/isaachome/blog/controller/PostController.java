package com.isaachome.blog.controller;

import java.util.List;

import com.isaachome.blog.payload.PostResponse;
import com.isaachome.blog.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.isaachome.blog.payload.PostDto;
import com.isaachome.blog.service.PostService;

import javax.validation.Valid;

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
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
		return new ResponseEntity<PostDto>(postService.createPost(postDto),HttpStatus.CREATED);
	}
	
	
	// get all posts restApi
	@GetMapping
	public PostResponse getAllPosts(
			@RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false)int pageNo,
			@RequestParam(value = "pageSize",defaultValue =AppConstants.DEFAULT_PAGE_SIZE,required = false)int pageSize,
			@RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false)String sortBy,
			@RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIRECTION,required = false)String sortDir
			){
		return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
	}
	//  get post by id restApi
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable(name="id") long id){
		return  ResponseEntity.ok(postService.getPostById(id));
	}
	
	// update post by id restApi
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<PostDto> updatePostById(@Valid @RequestBody PostDto postDto,
			@PathVariable(name="id")Long id){
		PostDto postResponse= postService.updatePost(postDto, id);
		return new ResponseEntity<>(postResponse,HttpStatus.OK);
		
	}
	// delete post by id restApi
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String>deletePostById (@PathVariable(name="id")long id) {
		postService.deletePostById(id);
		return new ResponseEntity<String>("Post entity deleted successfully",HttpStatus.OK);
	}
	
}
