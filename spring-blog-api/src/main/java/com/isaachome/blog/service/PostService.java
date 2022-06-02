package com.isaachome.blog.service;

import java.util.List;

import com.isaachome.blog.payload.PostDto;

/**
 *
 * @author IsaacHome
 */

public interface PostService {
	PostDto createPost (PostDto postDto);
	List<PostDto> getAllPosts();
}
