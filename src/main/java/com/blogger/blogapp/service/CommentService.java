package com.blogger.blogapp.service;


import com.blogger.blogapp.payload.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, Long id);

    void deleteComment(Long commentId);
}
