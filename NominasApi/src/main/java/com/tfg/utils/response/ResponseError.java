package com.tfg.utils.response;
import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.ObjectError;

public class ResponseError {
	
	private List<ObjectError> errors = new ArrayList<ObjectError>();
	
	public List<ObjectError> getErrors() {
		return errors;
	}

	public void setErrors(List<ObjectError> errors) {
		this.errors = errors;
	}


}
