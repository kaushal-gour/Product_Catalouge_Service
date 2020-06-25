package com.contentserve.products.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contentserve.products.model.Category;
import com.contentserve.products.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	Optional<List<Product>> findAllByCategory(Category category);
	
	Optional<List<Product>> findAllByBrand(String brand);
	

}
