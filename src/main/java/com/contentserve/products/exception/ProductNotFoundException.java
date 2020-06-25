package com.contentserve.products.exception;

public class ProductNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 5650568296312344547L;

	public ProductNotFoundException(String msg) {
		super(msg);
	}

}
