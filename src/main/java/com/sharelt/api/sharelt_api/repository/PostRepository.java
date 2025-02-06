package com.sharelt.api.sharelt_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sharelt.api.sharelt_api.entity.post.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByCreatedBy(Long userId);
}