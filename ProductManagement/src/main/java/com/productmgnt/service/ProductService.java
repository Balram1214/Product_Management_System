package com.productmgnt.service;

import java.util.List;

import com.productmgnt.dto.ProductDto;

public interface ProductService {

	public ProductDto createProduct(ProductDto productdto);

	public ProductDto updateProduct(ProductDto productdto, Integer productId);

	public boolean deleteProduct(Integer productId);

	public ProductDto getProductById(Integer productId);
	
	public List<ProductDto> getAllProducts();

	public ProductDto getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
}
