package com.blogger.blogapp.payload;


import com.blogger.blogapp.entities.Category;
import com.blogger.blogapp.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
public class PostDto {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private Date date;

    private String imageName;

    private CategoryDto category;

    private UserDto user;


}
