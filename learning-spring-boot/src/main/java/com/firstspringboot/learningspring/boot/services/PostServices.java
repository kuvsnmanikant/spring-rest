package com.firstspringboot.learningspring.boot.services;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.firstspringboot.learningspring.boot.dto.PostDto;
import com.firstspringboot.learningspring.boot.dto.PostResponse;
import com.firstspringboot.learningspring.boot.entries.Post;
import com.firstspringboot.learningspring.boot.exception.ResourceNotFoundException;
import com.firstspringboot.learningspring.boot.repository.PostRepository;
import com.firstspringboot.learningspring.boot.services.interfaces.PostService;

@Service
public class PostServices implements PostService {

    private PostRepository postRepository;
    private ModelMapper modelMapper;


    public PostServices(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    private PostDto mapToDto(Post post){

        PostDto postDto = modelMapper.map(post, PostDto.class);

        // PostDto postDto = new PostDto();  // this is mapping manually
        // postDto.setId(post.getId());
        // postDto.setTitle(post.getTitle());
        // postDto.setDescription(post.getDescription());
        // postDto.setContent(post.getContent());
        return postDto;
    }

    private Post mapToEntries(PostDto postDto){

        Post post = modelMapper.map(postDto, Post.class);
        return post;
    }

    @Override
    public PostDto creaPostDto(PostDto postDto) {
        
        Post newPost = postRepository.save(mapToEntries(postDto));
        return mapToDto(newPost);
    }

    @Override
    public PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {

        PageRequest pageable = PageRequest.of(pageNo, pageSize, sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending());
        Page<Post> pagePosts = postRepository.findAll(pageable);
        List<PostDto> posts = pagePosts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
        return new PostResponse(posts, pageNo, pageSize, pagePosts.getTotalElements(), pagePosts.getTotalPages(), pagePosts.isLast());
    }

    @Override
    public PostDto getPostById(long id) {
        return mapToDto(postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id)));
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {

        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return mapToDto(postRepository.save(post));
    }

    @Override
    public void deletePostById(long id) {

        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }
    
}
