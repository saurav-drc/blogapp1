package com.blogger.blogapp.repository;

import com.blogger.blogapp.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
