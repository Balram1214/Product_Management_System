package com.productmgnt.exception;


public class ResourceNotCreated extends RuntimeException{
	
	private String resourceName;

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public ResourceNotCreated(String resourceName) {
		super(String.format("%s resource not created", resourceName));
		this.resourceName = resourceName;
	}
	
	

}
