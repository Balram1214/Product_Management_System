package com.productmgnt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productmgnt.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

}
