package com.blogger.blogapp.service;

import com.blogger.blogapp.payload.CategoryDto;

import java.util.List;

public interface CategoryService {

    public CategoryDto createCategory(CategoryDto categoryDto);

    public CategoryDto updateCategory(CategoryDto categoryDto,Long categoryId);

    public void deleteCategory(Long categoryId);

    public CategoryDto getCategoryById(Long categoryId);

    public List<CategoryDto> getAllCategories();

}
