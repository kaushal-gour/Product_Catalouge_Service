package com.contentserve.products.exception;

public class CategoryNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -4631080710200995627L;
	
	public CategoryNotFoundException(String msg) {
		super(msg);
	}

}
