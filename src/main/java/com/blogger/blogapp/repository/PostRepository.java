package com.blogger.blogapp.repository;

import com.blogger.blogapp.entities.Post;
import com.blogger.blogapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findByUser(User user);
}
