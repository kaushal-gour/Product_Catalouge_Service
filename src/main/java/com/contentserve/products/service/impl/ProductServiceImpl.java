package com.contentserve.products.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contentserve.products.exception.CategoryNotFoundException;
import com.contentserve.products.exception.ProductNotFoundException;
import com.contentserve.products.model.Category;
import com.contentserve.products.model.Product;
import com.contentserve.products.repository.CategoryRepository;
import com.contentserve.products.repository.ProductRepository;
import com.contentserve.products.service.ProductService;
import com.contentserve.products.utils.BeanUtility;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	BeanUtility beanUtility;

	@Override
	public Product addProduct(Product product) {
		Optional<Category> cat = categoryRepository.findByName(product.getCategory().getName());
		if(cat.isPresent()) {
			product.setCategory(cat.get());
		} else {
			Category category = categoryRepository.save(product.getCategory());
			product.setCategory(category);
		}
		return productRepository.save(product);
	}


	@Override
	public List<Product> getProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProductById(Long id) {
		Optional<Product> product = productRepository.findById(id);
		if(!product.isPresent()) {
			throw new ProductNotFoundException("No Product found for id: "+id);
		} 
		return product.get();
	}

	@Override
	public List<Product> getProdyctByCategory(Long id) {
		Optional<Category> cat = categoryRepository.findById(id);
		if(!cat.isPresent()) {
			throw new CategoryNotFoundException("No category found for id: "+id);
		}
		
		Optional<List<Product>> products = productRepository.findAllByCategory(cat.get());
		if(!products.isPresent()) {
			throw new ProductNotFoundException("No product found for category of Id: "+id);
		}
		return products.get();
	}

	@Override
	public List<Product> getProdyctByCategoryName(String categoryName) {
		Optional<Category> cat = categoryRepository.findByName(categoryName);
		if(!cat.isPresent()) {
			throw new CategoryNotFoundException("No category found for name: "+categoryName);
		}
		
		Optional<List<Product>> products = productRepository.findAllByCategory(cat.get());
		if(!products.isPresent()) {
			throw new ProductNotFoundException("No product found for category of categoryName: "+categoryName);
		}
		return products.get();
	}

	@Override
	public List<Product> getProductsByBrand(String brand) {
		Optional<List<Product>> products = productRepository.findAllByBrand(brand);
		if(!products.isPresent()) {
			throw new ProductNotFoundException("No product found for brand: "+brand);
		}
		return products.get();
	}

	@Override
	public void deleteAllProducts() {
		List<Product> products = productRepository.findAll();
		if(null == products) {
			throw new ProductNotFoundException("No product found for deletion");
		}
		if(products.isEmpty()) {
			throw new ProductNotFoundException("No product found for deletion");
		}
		productRepository.deleteAll();
	}

	@Override
	public void deleteProductById(Long id) {
		Optional<Product> product = productRepository.findById(id);
		if(!product.isPresent()) {
			throw new ProductNotFoundException("No product found for deletion of id: "+id);
		}
		productRepository.deleteById(id);
		
	}

	@Override
	public Product updateProduct(Long id, Product product) {
		Optional<Product> existingProduct = productRepository.findById(id);
		if(!existingProduct.isPresent()) {
			throw new ProductNotFoundException("No Product found for id: "+id);
		}
		
		Product exsistP = existingProduct.get();
		beanUtility.copyNonNullProperties(product, exsistP);
		return productRepository.save(exsistP);
	}

}
