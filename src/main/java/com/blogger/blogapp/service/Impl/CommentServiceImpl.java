package com.blogger.blogapp.service.Impl;

import com.blogger.blogapp.entities.Category;
import com.blogger.blogapp.entities.Comment;
import com.blogger.blogapp.entities.Post;
import com.blogger.blogapp.exception.ResourceNotFoundException;
import com.blogger.blogapp.payload.CategoryDto;
import com.blogger.blogapp.payload.CommentDto;
import com.blogger.blogapp.payload.PostDto;
import com.blogger.blogapp.repository.CommentRepository;
import com.blogger.blogapp.repository.PostRepository;
import com.blogger.blogapp.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PostRepository postRepository;

    @Override
    public CommentDto createComment(CommentDto commentDto, Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        Comment comment = modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);

        return modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));
        commentRepository.delete(comment);

    }


}
