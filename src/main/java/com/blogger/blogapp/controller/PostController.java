package com.blogger.blogapp.controller;


import com.blogger.blogapp.payload.PostDto;
import com.blogger.blogapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    //CREATE
    @PostMapping("/user/{id}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable Long id,
                                              @PathVariable Long categoryId){
        PostDto createdPost = postService.createPost(postDto,id,categoryId);
        return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);

    }
}
