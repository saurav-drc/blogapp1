package com.blogger.blogapp.payload;
import com.blogger.blogapp.entities.Category;
import com.blogger.blogapp.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
public class PostDto {

    private long id;
    private String title;
    private String content;
    private Date date;
    private String imageName;

    private CategoryDto category;

    private UserDto user;

    //Using this we can directly get the comment of posts
    //we won't have to create a separate getmapping for comment
    //private Set<CommentDto> comments = new HashSet<>();
    private List<CommentDto> comments = new ArrayList<>();

}
