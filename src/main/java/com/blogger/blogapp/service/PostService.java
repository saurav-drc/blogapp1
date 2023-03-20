package com.blogger.blogapp.service;

import com.blogger.blogapp.entities.Post;
import com.blogger.blogapp.payload.PostDto;
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
    List<PostDto> getAllPosts();

    //DELETE
    void deletePost(PostDto postDto);

    //GET POST BY CATEGORY-ID
    List<Post> getPostByCategory(Long categoryId);

    //GET POST BY USER
    List<Post> getPostByUser(Long id);

    //Search posts

    List<Post> searchPost(String keyword);

}
