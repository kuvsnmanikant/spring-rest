package com.firstspringboot.learningspring.boot.services;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.firstspringboot.learningspring.boot.dto.CommentDto;
import com.firstspringboot.learningspring.boot.entries.Comment;
import com.firstspringboot.learningspring.boot.entries.Post;
import com.firstspringboot.learningspring.boot.exception.BlogAPIException;
import com.firstspringboot.learningspring.boot.exception.ResourceNotFoundException;
import com.firstspringboot.learningspring.boot.repository.CommentRepository;
import com.firstspringboot.learningspring.boot.repository.PostRepository;
import com.firstspringboot.learningspring.boot.services.interfaces.CommentService;

@Service
public class CommentServices implements CommentService{

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper modelMapper;

    public CommentServices(CommentRepository commentRepository, PostRepository postRepository,
            ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    private CommentDto mapToDto(Comment comment){

        CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
        return commentDto;
    }

    private Comment mapToEntries(CommentDto commentDto){

        Comment comment = modelMapper.map(commentDto, Comment.class);
        return comment;
    }

    private Comment validatePostAndComment(long postId, long commentId){

        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
        return comment;
    }

    @Override
    public CommentDto createCommentDto(long postId, CommentDto commentDto) {

        Comment comment = mapToEntries(commentDto);
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        comment.setPost(post);
        return mapToDto(commentRepository.save(comment));
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {

        return commentRepository.findByPostId(postId).stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {

        return mapToDto(validatePostAndComment(postId, commentId));
    }

    @Override
    public CommentDto updateComment(long postId, long commentId, CommentDto commentDto) {

        Comment comment = validatePostAndComment(postId, commentId);
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        return mapToDto(commentRepository.save(comment));
    }

    @Override
    public void deleteCommentById(long postId, long commentId) {

        Comment comment = validatePostAndComment(postId, commentId);
        commentRepository.delete(comment);
    }
}
