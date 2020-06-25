package com.contentserve.products.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.contentserve.products.dto.ErrorResponse;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(value = ProductNotFoundException.class)
	public ResponseEntity<ErrorResponse<String>> handleProductNotFoundException(ProductNotFoundException ex,
			WebRequest request) {
		ErrorResponse<String> errorResponse = new ErrorResponse<>(new Date(), ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(value = CategoryNotFoundException.class)
	public ResponseEntity<ErrorResponse<String>> handleCategoryNotFoundException(CategoryNotFoundException ex,
			WebRequest request) {
		ErrorResponse<String> errorResponse = new ErrorResponse<>(new Date(), ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
	public ResponseEntity<ErrorResponse<String>> handleGenericException(CategoryNotFoundException ex,
			WebRequest request) {
		ErrorResponse<String> errorResponse = new ErrorResponse<>(new Date(), "Please try after some time");
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Map<String, Map<String, String>> map = new HashMap<>();
		
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		for(FieldError error : fieldErrors) {
			Map<String, String> data = new HashMap<>();
			data.put("errorMessage", error.getDefaultMessage());
			map.put(error.getField(), data);
		}

		ErrorResponse<Map<String, Map<String, String>>> errorResponse = new ErrorResponse<>(new Date(), map);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

}
