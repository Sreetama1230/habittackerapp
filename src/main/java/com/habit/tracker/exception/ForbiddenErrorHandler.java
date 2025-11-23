package com.habit.tracker.exception;

import java.net.http.HttpClient;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.habit.tracker.error.ErrorDetails;

@ControllerAdvice
public class ForbiddenErrorHandler {

	@ExceptionHandler
	public ResponseEntity<ErrorDetails> handlerForbiddenError(ForbiddenError error){
		ErrorDetails ed = new ErrorDetails(error.getMessage(), HttpStatus.FORBIDDEN.value());
		
		return new ResponseEntity<ErrorDetails>(ed, HttpStatus.FORBIDDEN);
		
	}
}
