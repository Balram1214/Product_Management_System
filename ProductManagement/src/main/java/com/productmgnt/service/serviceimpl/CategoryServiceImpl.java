package com.productmgnt.service.serviceimpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.asm.Advice.This;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.productmgnt.dto.CategoryDto;
import com.productmgnt.entity.Category;
import com.productmgnt.repository.CategoryRepo;
import com.productmgnt.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelmapper;

	@Override
	public CategoryDto createCategory(CategoryDto categorydto) {

		Category category = this.modelmapper.map(categorydto, Category.class);

		Category saveCategory = this.categoryRepo.save(category);

		return this.modelmapper.map(saveCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categorydto, Integer categoryId) {

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new RuntimeException("Category id not found"));

		category.setDescription(categorydto.getDescription());
		category.setTitle(categorydto.getTitle());

		Category updateCategory = this.categoryRepo.save(category);

		return this.modelmapper.map(updateCategory, CategoryDto.class);
	}

	@Override
	public boolean deleteCategory(Integer categoryId) {

		Optional<Category> category = this.categoryRepo.findById(categoryId);

		if (category.isPresent()) {
			this.categoryRepo.deleteById(categoryId);

			return true;
		}
		return false;
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {

		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new RuntimeException("Category id not found"));

		return this.modelmapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() {

		List<Category> categories=this.categoryRepo.findAll();
		
		if(categories.isEmpty()) {
			return Collections.emptyList();
		}
		
		return categories.stream().map((category)-> this.modelmapper.map(categories, CategoryDto.class)).collect(Collectors.toList());
		
	}

	
}
