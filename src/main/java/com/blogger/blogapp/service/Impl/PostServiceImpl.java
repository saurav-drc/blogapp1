package com.blogger.blogapp.service.Impl;

import com.blogger.blogapp.entities.Category;
import com.blogger.blogapp.entities.Post;
import com.blogger.blogapp.entities.User;
import com.blogger.blogapp.exception.ResourceNotFoundException;
import com.blogger.blogapp.payload.PostDto;
import com.blogger.blogapp.repository.CategoryRepository;
import com.blogger.blogapp.repository.PostRepository;
import com.blogger.blogapp.repository.UserRepository;
import com.blogger.blogapp.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public PostDto createPost(PostDto postDto,Long id,Long categoryId) {
        //first fetch the user and category for the post
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        Post post = modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post savedPost = postRepository.save(post);
        return modelMapper.map(savedPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Long id) {
        return null;
    }

    @Override
    public PostDto getPostById(Long id) {
        return null;
    }

    @Override
    public List<PostDto> getAllPosts() {
        return null;
    }

    @Override
    public void deletePost(PostDto postDto) {

    }

    @Override
    public List<Post> getPostByCategory(Long categoryId) {
        return null;
    }

    @Override
    public List<Post> getPostByUser(Long id) {
        return null;
    }

    @Override
    public List<Post> searchPost(String keyword) {
        return null;
    }
}
