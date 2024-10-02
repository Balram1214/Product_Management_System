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
import com.productmgnt.dto.ProductDto;
import com.productmgnt.exception.ResourceNotCreated;
import com.productmgnt.exception.ResourceNotFound;
import com.productmgnt.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	@Autowired
	private ProductService productServiceImpl;

	@PostMapping("/create/")
	public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto) {

		ApiResponse apiResponse = new ApiResponse();

		try {
			ProductDto product = productServiceImpl.createProduct(productDto);

			apiResponse.setData(product);
			apiResponse.setError(false);
			apiResponse.setIs_Success(true);
			apiResponse.setMessage("Product created successfully!!");

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.CREATED);

		} catch (ResourceNotCreated resourceNotCreated) {

			apiResponse.setError(true);
			apiResponse.setIs_Success(false);
			apiResponse.setMessage(resourceNotCreated.getMessage());

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/{productId}")
	public ResponseEntity<ApiResponse> getProductById(@PathVariable Integer productId) {

		ApiResponse apiResponse = new ApiResponse();

		try {
			ProductDto product = productServiceImpl.getProductById(productId);

			apiResponse.setData(product);
			apiResponse.setIs_Success(true);
			apiResponse.setMessage("Product created successfully!!");

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);

		} catch (ResourceNotFound resourceNotFound) {

			apiResponse.setError(true);
			apiResponse.setIs_Success(false);
			apiResponse.setMessage("product not retrived");

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/")
	public ResponseEntity<ApiResponse> getAllProducts(
			@RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortby", defaultValue = AppConstant.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstant.SORT_DIR, required = false) String sortDir) {

		ApiResponse apiResponse = new ApiResponse();

		List<ProductDto> products = (List<ProductDto>) this.productServiceImpl.getAllProducts(pageNumber, pageSize,
				sortBy, sortDir);

		if (!products.isEmpty()) {
			apiResponse.setData(products);
			apiResponse.setIs_Success(true);
			apiResponse.setMessage("All products recieved successfully!!");

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
		} else {
			apiResponse.setError(false);
			apiResponse.setIs_Success(false);
			apiResponse.setMessage("All products not recieved successfully!!");

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);

		}
	}

	@PutMapping("/{productId}")
	public ResponseEntity<ApiResponse> updateProduct(@PathVariable Integer productId,
			@RequestParam("productName") String productName, @RequestParam("price") double price,
			@RequestParam("description") String description) {

		ProductDto product = new ProductDto();

		product.setDescription(description);
		product.setPrice(price);
		product.setProductName(productName);

		ApiResponse apiResponse = new ApiResponse();

		try {
			ProductDto productdto = this.productServiceImpl.updateProduct(product, productId);

			apiResponse.setData(productdto);
			apiResponse.setError(false);
			apiResponse.setIs_Success(true);
			apiResponse.setMessage("Product updated successfully!!");

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.CREATED);

		} catch (ResourceNotFound resourceNotFound) {

			apiResponse.setError(true);
			apiResponse.setIs_Success(false);
			apiResponse.setMessage(resourceNotFound.getMessage());

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/{productId}")
	public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Integer productId) {

		ApiResponse apiResponse = new ApiResponse();

		boolean deleteById = this.productServiceImpl.deleteProduct(productId);

		if (deleteById == true) {
			apiResponse.setData(deleteById);
			apiResponse.setMessage("product deleted successfully!!");

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
		} else {
			apiResponse.setIs_Success(false);
			apiResponse.setError(true);
			apiResponse.setMessage("product not deleted successfully");

			return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);
		}

	}

}
