package com.blogger.blogapp.controller;


import com.blogger.blogapp.config.AppConstants;
import com.blogger.blogapp.payload.ApiResponse;
import com.blogger.blogapp.payload.PostDto;
import com.blogger.blogapp.payload.PostResponse;
import com.blogger.blogapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    //CREATE
    @PostMapping("/user/{id}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost( @RequestBody PostDto postDto, @PathVariable Long id,
                                              @PathVariable Long categoryId){
        PostDto createdPost = postService.createPost(postDto,id,categoryId);
        return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);

    }

    //Get Post By user
    @GetMapping("/user/{id}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Long id){
        List<PostDto> posts = postService.getPostsByUser(id);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);

    }

    //Get posts by Category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Long categoryId){
        List<PostDto> posts = postService.getPostsByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }

    //Get post by id
    @GetMapping("/posts/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id){
        PostDto postDto = postService.getPostById(id);
        return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
    }

    //Get All Posts
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value="pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) int pageNumber,
            @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue =AppConstants.SORT_BY,required = false)String sortBy
    ){
        PostResponse postResponse = postService.getAllPosts(pageNumber, pageSize,sortBy);
        return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Long id){
        postService.deletePost(id);
        return new ResponseEntity<>(new ApiResponse("post deleted successfully",true),HttpStatus.OK);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Long id){
        PostDto updatedPost = postService.updatePost(postDto, id);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);

    }

}
