package com.productmgnt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.productmgnt.configuration.AppConstant;
import com.productmgnt.dto.ApiResponse;
import com.productmgnt.dto.CategoryDto;
import com.productmgnt.exception.ResourceNotCreated;
import com.productmgnt.exception.ResourceNotFound;
import com.productmgnt.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryServiceImpl;

	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createProduct(@RequestBody CategoryDto categoryDto) {

		ApiResponse apiResponse = new ApiResponse();

		try {
			CategoryDto category = categoryServiceImpl.createCategory(categoryDto);

			apiResponse.setData(category);
			apiResponse.setError(false);
			apiResponse.setIs_Success(true);
			apiResponse.setMessage("Category created successfully!!");

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.CREATED);

		} catch (ResourceNotCreated resourceNotCreated) {

			apiResponse.setError(true);
			apiResponse.setIs_Success(false);
			apiResponse.setMessage(resourceNotCreated.getMessage());

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Integer categoryId) {

		ApiResponse apiResponse = new ApiResponse();

		try {
			CategoryDto category = categoryServiceImpl.getCategoryById(categoryId);

			apiResponse.setData(category);
			apiResponse.setIs_Success(true);
			apiResponse.setMessage("Category created successfully!!");

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);

		} catch (ResourceNotFound resourceNotFound) {

			apiResponse.setError(true);
			apiResponse.setIs_Success(false);
			apiResponse.setMessage("category not retrived");

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/")
	public ResponseEntity<ApiResponse> getAllProducts() {

		ApiResponse apiResponse = new ApiResponse();
		
		List<CategoryDto> categories=this.categoryServiceImpl.getAllCategories();

		if (!categories.isEmpty()) {
			apiResponse.setData(categories);
			apiResponse.setIs_Success(true);
			apiResponse.setMessage("All Categories recieved successfully!!");

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
		} else {
			apiResponse.setError(false);
			apiResponse.setIs_Success(false);
			apiResponse.setMessage("All Categories not recieved successfully!!");

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);

		}
	}

	@PutMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> updateCategory(@PathVariable Integer categoryId,
			@RequestParam("title") String title, @RequestParam("description") String description) {

		CategoryDto category = new CategoryDto();

		category.setTitle(title);
		category.setDescription(description);

		ApiResponse apiResponse = new ApiResponse();

		try {
			CategoryDto categorydto = this.categoryServiceImpl.updateCategory(category, categoryId);

			apiResponse.setData(categorydto);
			apiResponse.setError(false);
			apiResponse.setIs_Success(true);
			apiResponse.setMessage("Category updated successfully!!");

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.CREATED);

		} catch (ResourceNotFound resourceNotFound) {

			apiResponse.setError(true);
			apiResponse.setIs_Success(false);
			apiResponse.setMessage(resourceNotFound.getMessage());

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {

		ApiResponse apiResponse = new ApiResponse();

		boolean deleteById = this.categoryServiceImpl.deleteCategory(categoryId);

		if (deleteById == true) {
			apiResponse.setData(deleteById);
			apiResponse.setMessage("Category deleted successfully!!");

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
		} else {
			apiResponse.setIs_Success(false);
			apiResponse.setError(true);
			apiResponse.setMessage("Category not deleted successfully");

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
		}

	}
}
