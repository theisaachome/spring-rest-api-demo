package com.isaachome.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.isaachome.blog.payload.PostResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.isaachome.blog.entity.Post;
import com.isaachome.blog.exception.ResourceNotFoundException;
import com.isaachome.blog.payload.PostDto;
import com.isaachome.blog.repos.PostRepo;
import com.isaachome.blog.service.PostService;

/**
 *
 * @author IsaacHome
 */

@Service
public class PostServiceImpl implements PostService {
	
	private PostRepo postRepo;
	
	private ModelMapper modelMapper;
	public PostServiceImpl(PostRepo postRepo,ModelMapper modelMapper) {
		super();
		this.postRepo = postRepo;
		this.modelMapper = modelMapper;
	}


	@Override
	public PostDto createPost(PostDto postDto) {
		Post newPost= postRepo.save(mapToEntity(postDto));
		return mapToDto(newPost);
	}


	@Override
	public PostResponse getAllPosts(int pageNo,int pageSize,String sortBy,String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():
				Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(pageNo,pageSize, sort);
		Page<Post> posts = postRepo.findAll(pageable);
		List<Post> postList = posts.getContent();
		List<PostDto> content= postList.stream().map(p->mapToDto(p)).collect(Collectors.toList());
		PostResponse postResponse = new PostResponse(
				content,
				pageNo,
				pageSize,posts.getTotalElements(),posts.getTotalPages(),posts.isLast());
		return postResponse;
	}

	@Override
	public PostDto getPostById(long id) {
		Post post = postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
		PostDto  postDto= mapToDto(post);
		return postDto;
	}
	
	
	@Override
	public void deletePostById(long id) {
		Post post = postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
		postRepo.delete(post);
	}


	@Override
	public PostDto updatePost(PostDto postDto, long id) {
		// get post by id from database 
		// if not found post throw exception
		Post post = postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
		
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		
		Post updatedPost = postRepo.save(post);
		return mapToDto(updatedPost);
	}


	private PostDto mapToDto(Post post) {
//		PostDto postDto = new PostDto();
//		postDto.setId(post.getId());
//		postDto.setTitle(post.getTitle());
//		postDto.setContent(post.getContent());
//		postDto.setDescription(post.getDescription());
		PostDto postDto = modelMapper.map(post,PostDto.class);
		return postDto;
		
	}
	
	private Post mapToEntity (PostDto postDto) {
//		Post post = new Post();
//		post.setTitle(postDto.getTitle());
//		post.setDescription(postDto.getDescription());
//		post.setContent(postDto.getContent());
		Post post = modelMapper.map(postDto,Post.class);
		return post;
	}


}
