package com.isaachome.blog.service;

import com.isaachome.blog.payload.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(long postId,CommentDto commentDto);
    List<CommentDto> getCommentsByPostId(long postId);
    CommentDto getCommentById(long postId,long commentId);
    CommentDto updateComment(Long postId,long commentId,CommentDto commentDto);
    void deleteComment(Long postId,Long commentId);
}
