package com.productmgnt.service.serviceimpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.productmgnt.dto.ProductDto;
import com.productmgnt.entity.Product;
import com.productmgnt.repository.ProductRepo;
import com.productmgnt.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ProductDto createProduct(ProductDto productdto) {
		Product product = this.modelMapper.map(productdto, Product.class);

		Product saveProduct = this.productRepo.save(product);

		return this.modelMapper.map(saveProduct, ProductDto.class);

	}

	@Override
	public ProductDto updateProduct(ProductDto productdto, Integer productId) {

		Product product = this.productRepo.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product Id Not Found"));

		product.setProductName(productdto.getProductName());

		product.setPrice(productdto.getPrice());

		Product updateProduct = this.productRepo.save(product);

		return this.modelMapper.map(updateProduct, ProductDto.class);
	}

	@Override
	public boolean deleteProduct(Integer productId) {

		Optional<Product> product = this.productRepo.findById(productId);

		if (product.isPresent()) {
			this.productRepo.deleteById(productId);

			return true;
		}

		return false;

	}

	@Override
	public ProductDto getProductById(Integer productId) {

		Product product = this.productRepo.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product Id Not Found"));

		return this.modelMapper.map(product, ProductDto.class);
	}

	@Override
	public ProductDto getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

		Sort sort = null;

		if (sortDir.equalsIgnoreCase("asc")) {
			sort = sort.by(sortBy).ascending();
		} else {
			sort = sort.by(sortBy).descending();
		}

		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

		Page<Product> product = this.productRepo.findAll(pageable);

		List<Product> products = product.getContent();

		if (products.isEmpty()) {
			return (ProductDto) Collections.emptyList();
		}

		return (ProductDto) products.stream().map((product1) -> this.modelMapper.map(product1, ProductDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<ProductDto> getAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

}
