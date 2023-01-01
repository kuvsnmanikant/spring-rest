package com.firstspringboot.learningspring.boot.services.interfaces;

import com.firstspringboot.learningspring.boot.dto.PostDto;
import com.firstspringboot.learningspring.boot.dto.PostResponse;

public interface PostService {

    PostDto creaPostDto(PostDto postDto);
    PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);
    PostDto getPostById(long id);
    PostDto updatePost(PostDto postDto, long id);
    void deletePostById(long id);
}
