package com.mercadolibre.model;

public class ErrorException extends Exception {

	private Integer code;
	
	public ErrorException(Integer code, String message) {
		super(message);
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

}
