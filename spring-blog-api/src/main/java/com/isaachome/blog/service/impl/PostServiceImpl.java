package com.isaachome.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.isaachome.blog.entity.Post;
import com.isaachome.blog.payload.PostDto;
import com.isaachome.blog.repos.PostRepos;
import com.isaachome.blog.service.PostService;

/**
 *
 * @author IsaacHome
 */

@Service
public class PostServiceImpl implements PostService {
	
	private PostRepos postRepos;
	

	public PostServiceImpl(PostRepos postRepos) {
		super();
		this.postRepos = postRepos;
	}


	@Override
	public PostDto createPost(PostDto postDto) {
		Post newPost=postRepos.save(mapToEntity(postDto));
		return mapToDto(newPost);
	}


	@Override
	public List<PostDto> getAllPosts() {
		return postRepos.findAll().stream().map(p->mapToDto(p)).collect(Collectors.toList());
	}
	
	private PostDto mapToDto(Post post) {
		PostDto postDto = new PostDto();
		postDto.setId(post.getId());
		postDto.setTitle(post.getTitle());
		postDto.setContent(post.getContent());
		postDto.setDescription(post.getDescription());
		return postDto;
		
	}
	
	private Post mapToEntity (PostDto postDto) {
		Post post = new Post();
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		
		return post;
	}

}