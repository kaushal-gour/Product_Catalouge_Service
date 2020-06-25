package com.contentserve.products.service;

import java.util.List;

import com.contentserve.products.model.Product;

public interface ProductService {
	
	public Product addProduct(Product product);
	
	public List<Product> getProducts();
	
	public List<Product> getProductsByBrand(String brand);
	
	public Product getProductById(Long id);
	
	public List<Product> getProdyctByCategory(Long id);
	
	public List<Product> getProdyctByCategoryName(String categoryName);
	
	public void deleteAllProducts();
	
	public void deleteProductById(Long id);
	
	public Product updateProduct(Long id, Product product);

}
