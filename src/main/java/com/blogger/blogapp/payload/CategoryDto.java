package com.blogger.blogapp.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class CategoryDto {

    private long categoryId;
    @NotBlank
    @Size(min=2,message = "Title must be minimum of 2 characters")
    private String categoryTitle;
    @NotBlank
    private String categoryDescription;

}
