package com.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

//All the exception caught by the class -> annotation used controller advice
@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(DuplicateEmailException.class)
	
	public ResponseEntity<ErrorDetails> handleDuplicateEmailException(DuplicateEmailException e, WebRequest webRequest){
		
		ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), webRequest.getDescription(false));
		
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleUserNotFoundException( UserNotFoundException e, WebRequest webRequest){
		
		ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(), webRequest.getDescription(false));
		
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
		
	}	
	

}
