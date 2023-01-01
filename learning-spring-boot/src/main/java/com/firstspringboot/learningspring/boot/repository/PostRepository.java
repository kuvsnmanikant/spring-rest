package com.firstspringboot.learningspring.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.firstspringboot.learningspring.boot.entries.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
