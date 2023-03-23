package com.blogger.blogapp.service;

import com.blogger.blogapp.entities.Post;
import com.blogger.blogapp.payload.PostDto;
import com.blogger.blogapp.payload.PostResponse;
import javafx.geometry.Pos;

import java.util.List;

public interface PostService {

    //CREATE
    PostDto createPost(PostDto postDto,Long id,Long categoryId);

    //UPDATE
    PostDto updatePost(PostDto postDto,Long id);

    //GET POST BY ID
    PostDto getPostById(Long id);

    //GET ALL POST
    PostResponse getAllPosts(int pageNumber, int pageSize,String sortBy);

    //DELETE
    void deletePost(Long id);

    //GET POST BY CATEGORY-ID
    List<PostDto> getPostsByCategory(Long categoryId);

    //GET POST BY USER
    List<PostDto> getPostsByUser(Long id);

    //Search posts

    List<Post> searchPost(String keyword);

}
