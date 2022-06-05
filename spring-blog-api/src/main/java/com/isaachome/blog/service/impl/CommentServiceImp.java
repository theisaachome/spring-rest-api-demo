package com.isaachome.blog.service.impl;

import com.isaachome.blog.entity.Comment;
import com.isaachome.blog.entity.Post;
import com.isaachome.blog.exception.BlogAPIException;
import com.isaachome.blog.exception.ResourceNotFoundException;
import com.isaachome.blog.payload.CommentDto;
import com.isaachome.blog.repos.CommentRepo;
import com.isaachome.blog.repos.PostRepo;
import com.isaachome.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImp implements CommentService {

    private CommentRepo commentRepos;
    private PostRepo postRepo;

    private ModelMapper modelMapper;

    public CommentServiceImp(CommentRepo commentRepo, PostRepo postRepo,ModelMapper modelMapper) {
        this.commentRepos = commentRepo;
        this.postRepo = postRepo;
        this.modelMapper= modelMapper;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);
        Post post = postRepo.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException("Post","id",postId));
        // set post to comment entity
        comment.setPost(post);
       Comment newComment=  commentRepos.save(comment);
        return mapToDTO(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        List<Comment> comments = commentRepos.findByPostId(postId);
        return comments.stream().map((c)-> mapToDTO(c)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {
        Comment comment =getEntityComment(postId,commentId);
        return mapToDTO(comment);
    }

    @Override
    public CommentDto updateComment(Long postId, long commentId, CommentDto commentDto) {
        Comment comment = getEntityComment(postId,commentId);
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        Comment updatedComment = commentRepos.save(comment);
        return mapToDTO(updatedComment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
       Comment comment = getEntityComment(postId,commentId);
       commentRepos.delete(comment);
    }

    private  Comment getEntityComment(long postId,long commentId){
        Post post = postRepo.findById(postId).orElseThrow(()->
                new ResourceNotFoundException("Post","id",postId));
        Comment comment =commentRepos.findById(commentId)
                .orElseThrow(()-> new ResourceNotFoundException("Comment","id",commentId));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belong to a post.");
        }
        return comment;
    }
    private CommentDto mapToDTO(Comment comment){
        CommentDto dto = modelMapper.map(comment,CommentDto.class);
        return  dto;
    }
    private  Comment mapToEntity(CommentDto dto){
        Comment comment = modelMapper.map(dto,Comment.class);
        return  comment;
    }
}
