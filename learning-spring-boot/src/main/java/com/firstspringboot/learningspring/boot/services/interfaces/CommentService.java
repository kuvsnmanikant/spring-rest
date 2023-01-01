package com.firstspringboot.learningspring.boot.services.interfaces;

import java.util.List;
import com.firstspringboot.learningspring.boot.dto.CommentDto;

public interface CommentService {

    CommentDto createCommentDto(long postId, CommentDto commentDto); 
    List<CommentDto> getCommentsByPostId(long postId);
    CommentDto getCommentById(long postId, long commentId);
    CommentDto updateComment(long postId, long commentId, CommentDto commentDto);
    void deleteCommentById(long postId, long commentId);
}
