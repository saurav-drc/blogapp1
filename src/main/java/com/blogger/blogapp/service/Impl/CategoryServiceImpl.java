package com.blogger.blogapp.service.Impl;


import com.blogger.blogapp.entities.Category;
import com.blogger.blogapp.exception.ResourceNotFoundException;
import com.blogger.blogapp.payload.CategoryDto;
import com.blogger.blogapp.repository.CategoryRepository;
import com.blogger.blogapp.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category cat = modelMapper.map(categoryDto, Category.class);
        Category savedCat = categoryRepository.save(cat);
        return modelMapper.map(savedCat, CategoryDto.class);

    }
    @Override
    public CategoryDto updateCategory(CategoryDto category, Long categoryId) {
        Category cat = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        cat.setCategoryTitle(category.getCategoryTitle());
        cat.setCategoryDescription(category.getCategoryDescription());
        Category updatedCategory = categoryRepository.save(cat);
        return modelMapper.map(updatedCategory, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category cat = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        categoryRepository.delete(cat);

    }

    @Override
    public CategoryDto getCategoryById(Long categoryId) {
        Category cat = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        return modelMapper.map(cat, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = categories.stream().map((cat) -> modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
        return categoryDtos;
    }
}
