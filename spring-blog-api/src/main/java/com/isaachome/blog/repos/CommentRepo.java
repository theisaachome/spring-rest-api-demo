package com.isaachome.blog.repos;

import com.isaachome.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment,Long> {
    List<Comment> findByPostId(long postId);
}
