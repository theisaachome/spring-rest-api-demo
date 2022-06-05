package com.isaachome.blog.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isaachome.blog.entity.Post;

public interface PostRepo extends JpaRepository<Post, Long> {

}
