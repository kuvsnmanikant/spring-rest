package com.firstspringboot.learningspring.boot.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.firstspringboot.learningspring.boot.dto.CommentDto;
import com.firstspringboot.learningspring.boot.services.interfaces.CommentService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping(value = "/posts/{postId}/comments", params= "version=1")  // using params to version the api
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommentDto> createComment(@PathVariable("postId") long postId, @Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createCommentDto(postId, commentDto), HttpStatus.CREATED);
    }

    @GetMapping(value = "/posts/{postId}/comments", params = "version=1")
    @PreAuthorize("hasRole('ADMIN')"+"|| hasRole('USER')")
    public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable("postId") long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
    }

    @GetMapping(value = "/posts/{postId}/comments/{commentId}",headers = "version=1") // using headers to version the api
    @PreAuthorize("hasRole('ADMIN')"+"|| hasRole('USER')")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable("postId") long postId, @PathVariable("commentId") long commentId){
        return ResponseEntity.ok(commentService.getCommentById(postId, commentId));
    }

    @PutMapping(value = "/posts/{postId}/comments/{commentId}",produces = "application/version1+json") // using content negotiation to version the api
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommentDto> updateComment(@PathVariable("postId") long postId, @Valid @PathVariable("commentId") long commentId, @RequestBody CommentDto commentDto){
        return ResponseEntity.ok(commentService.updateComment(postId, commentId, commentDto));
    }

    @DeleteMapping(value = "/posts/{postId}/comments/{commentId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deletePostById(@PathVariable("postId") long postId, @PathVariable("commentId") long commentId){
        commentService.deleteCommentById(postId, commentId);
        return ResponseEntity.ok("Comment deleted successfully");
    }
}