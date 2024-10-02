package com.productmgnt.exception;

import lombok.Getter;

@Getter
public class ResourceNotFound extends RuntimeException{

	public String resourceName;
	public String fieldName;
	public Integer fieldValue;
	
	
	public ResourceNotFound(String resourceName, String fieldName, Integer fieldValue) {
		super(String.format("%s not found with %s", resourceName,fieldName,fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}


	public String getResourceName() {
		return resourceName;
	}


	public String getFieldName() {
		return fieldName;
	}


	public Integer getFieldValue() {
		return fieldValue;
	}
	
	
}
