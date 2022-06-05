package com.isaachome.blog.controller;

import com.isaachome.blog.payload.CommentDto;
import com.isaachome.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    private CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment (@PathVariable(name = "postId") long postId,
                                                    @Valid @RequestBody CommentDto commentDto){
    return  new ResponseEntity<>(service.createComment(postId,commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId (@PathVariable(name = "postId") long postId){
        return  service.getCommentsByPostId(postId);
    }

    @GetMapping("/posts/{postId}/comments/{id}")
    public  ResponseEntity<CommentDto> getCommentById(@PathVariable(name="postId") Long postId,
                                                      @PathVariable(name="id")Long commentId){
            CommentDto commentDto = service.getCommentById(postId,commentId);
            return new ResponseEntity<>(commentDto,HttpStatus.OK);
    }
    @PutMapping("/posts/{postId}/comments/{id}")
    public  ResponseEntity<CommentDto> updateComment(@PathVariable(name="postId") Long postId,
                                                      @PathVariable(name="id")Long commentId,
                                                   @Valid @RequestBody CommentDto commentDto){
        CommentDto updatedComment = service.updateComment(postId,commentId,commentDto);
        return new ResponseEntity<>(updatedComment,HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String>deleteCommentById (@PathVariable(name="postId")long postId,
                                                    @PathVariable(name="id")Long commentId) {
        service.deleteComment(postId,commentId);
        return new ResponseEntity<String>("Comment entity deleted successfully",HttpStatus.OK);
    }
}
