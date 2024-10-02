package com.productmgnt.service;

import java.util.List;

import com.productmgnt.dto.CategoryDto;

public interface CategoryService {
	
	public CategoryDto createCategory(CategoryDto categorydto);
	
	public CategoryDto updateCategory(CategoryDto categorydto, Integer categoryId);
	
	public boolean deleteCategory(Integer categoryId);
	
	public CategoryDto getCategoryById(Integer categoryId);
	
	public List<CategoryDto> getAllCategories();

}
