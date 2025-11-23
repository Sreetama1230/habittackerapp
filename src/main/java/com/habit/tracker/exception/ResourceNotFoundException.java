package com.habit.tracker.exception;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(String str){
		super(str);
	}
	public ResourceNotFoundException(){
		super();
	}
}
