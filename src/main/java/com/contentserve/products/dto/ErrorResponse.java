package com.contentserve.products.dto;

import java.util.Date;

public class ErrorResponse<T> {

	private Date timestamp;
	private T error;

	public ErrorResponse(Date timestamp, T error) {
		super();
		this.timestamp = timestamp;
		this.error = error;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public T getError() {
		return error;
	}

	public void setError(T error) {
		this.error = error;
	}

}