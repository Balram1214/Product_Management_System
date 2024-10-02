package com.productmgnt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.productmgnt.dto.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFound resourceNotFound)
	{
		String message=resourceNotFound.getMessage();
		
		ApiResponse apiResponse =new ApiResponse();
		
		apiResponse.setMessage(message);
		apiResponse.setData(null);
		apiResponse.setError(false);
		apiResponse.setIs_Success(false);
		
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ResourceNotCreated.class)
	public ResponseEntity<ApiResponse> resourceNotcreatedException(ResourceNotCreated resourceNotCreated)
	{
		String message=resourceNotCreated.getMessage();
		
		ApiResponse apiResponse =new ApiResponse();
		
		apiResponse.setMessage(message);
		apiResponse.setData(null);
		apiResponse.setError(false);
		apiResponse.setIs_Success(false);
		
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
}
