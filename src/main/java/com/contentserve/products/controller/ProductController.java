package com.contentserve.products.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.contentserve.products.model.Product;
import com.contentserve.products.service.ProductService;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

	@Autowired
	ProductService service;

	@PostMapping("/product")
	public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product) {

		product = service.addProduct(product);

		return new ResponseEntity<>(product, HttpStatus.CREATED);
	}

	@GetMapping("/product")
	public ResponseEntity<List<Product>> getProducts(@RequestParam(required = false) String brand) {
		List<Product> products = null;
		if (brand == null) {
			products = service.getProducts();
		} else {
			products = service.getProductsByBrand(brand);
		}
		return new ResponseEntity<>(products, HttpStatus.OK);

	}

	@GetMapping("/product/{id}")
	public ResponseEntity<?> getProductByID(@PathVariable Long id) {
		Product product = service.getProductById(id);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@GetMapping("/product/category/{categoryId}")
	public ResponseEntity<List<Product>> getProducts(@PathVariable Long categoryId) {
		List<Product> products = service.getProdyctByCategory(categoryId);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@GetMapping("/product/category")
	public ResponseEntity<List<Product>> getProductsByCategoryName(@RequestParam(value = "cname") String categoryName) {
		List<Product> products = service.getProdyctByCategoryName(categoryName);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@DeleteMapping("/product")
	public ResponseEntity<String> deleteProduct() {
		service.deleteAllProducts();
		return new ResponseEntity<>("All products deleted successfully",HttpStatus.OK);
	}
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
		service.deleteProductById(id);
		return new ResponseEntity<>("Product with id: "+ id +" deleted successfully",HttpStatus.OK);
	}
	
	@PutMapping("/product/{id}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable Long id) {
		product = service.updateProduct(id, product);
		return new ResponseEntity<>(product, HttpStatus.OK);
		
	}

}
