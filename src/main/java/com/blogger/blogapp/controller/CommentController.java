package com.blogger.blogapp.controller;

import com.blogger.blogapp.payload.ApiResponse;
import com.blogger.blogapp.payload.CommentDto;
import com.blogger.blogapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{id}/comment")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable Long id){
        CommentDto createdComment = commentService.createComment(commentDto, id);
        return new ResponseEntity<CommentDto>(createdComment, HttpStatus.CREATED);

    }

    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("comment deleted successfully",true),HttpStatus.OK);

    }

}
