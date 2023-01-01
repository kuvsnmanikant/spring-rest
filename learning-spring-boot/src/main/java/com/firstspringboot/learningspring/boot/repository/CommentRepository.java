package com.firstspringboot.learningspring.boot.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.firstspringboot.learningspring.boot.entries.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(long psotId);
}
