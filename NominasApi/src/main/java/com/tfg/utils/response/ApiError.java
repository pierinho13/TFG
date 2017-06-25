package com.tfg.utils.response;

import org.springframework.validation.ObjectError;

public class ApiError extends ObjectError{


	public ApiError() {
		super("","");
	}
	public ApiError(String objectName, String defaultMessage) {
		super(objectName, defaultMessage);
		// TODO Auto-generated constructor stub
	}
	
	public ApiError(String objectName, String[] codes, Object[] arguments, String defaultMessage) {
		super(objectName, codes, arguments, defaultMessage);
	}

	private static final long serialVersionUID = 4621627677468036790L;
}
