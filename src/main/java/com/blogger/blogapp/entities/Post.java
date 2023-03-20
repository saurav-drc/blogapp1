package com.blogger.blogapp.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String title;
    @Column(length = 1000,nullable = false)
    private String content;
    private String imageName;
    private Date date;


    @ManyToOne
    private Category category;

    @ManyToOne
    private User user;



}
