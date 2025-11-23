package com.habit.tracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.habit.tracker.error.ErrorDetails;

@ControllerAdvice
public class ResourceNotFoundExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException re){
		ErrorDetails ed = new ErrorDetails(re.getMessage(), HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ErrorDetails>(ed, HttpStatus.NOT_FOUND);
	}
}
