package com.blogger.blogapp.service.Impl;

import com.blogger.blogapp.entities.Category;
import com.blogger.blogapp.entities.Post;
import com.blogger.blogapp.entities.User;
import com.blogger.blogapp.exception.ResourceNotFoundException;
import com.blogger.blogapp.payload.PostDto;
import com.blogger.blogapp.payload.PostResponse;
import com.blogger.blogapp.repository.CategoryRepository;
import com.blogger.blogapp.repository.PostRepository;
import com.blogger.blogapp.repository.UserRepository;
import com.blogger.blogapp.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);
        return modelMapper.map(updatedPost, PostDto.class);

    }

    @Override
    public PostDto getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostResponse getAllPosts(int pageNumber, int pageSize,String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by(sortBy).ascending());
        Page<Post> pagePost = postRepository.findAll(pageable);
        List<Post> posts = pagePost.getContent();
        List<PostDto> postDtos = posts.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());

        return postResponse;
    }

    @Override
    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);

    }

    @Override
    public List<PostDto> getPostsByCategory(Long categoryId) {
        Category cat = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        List<Post> posts = postRepository.findByCategory(cat);
        List<PostDto> postDtos = posts.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> getPostsByUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        List<Post> posts = postRepository.findByUser(user);
        List<PostDto> postDtos = posts.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;

    }

    @Override
    public List<Post> searchPost(String keyword) {
        return null;
    }
}
