package com.isaachome.blog.service;

import java.util.List;

import com.isaachome.blog.entity.Post;
import com.isaachome.blog.payload.PostDto;
import com.isaachome.blog.payload.PostResponse;

/**
 *
 * @author IsaacHome
 */

public interface PostService {
	PostDto createPost (PostDto postDto);
	PostResponse getAllPosts(int pageNo, int pageSize,String sortBy,String sortDir);
	PostDto  getPostById(long id);
	PostDto updatePost(PostDto postDto,long id);
	void deletePostById(long id);
}
