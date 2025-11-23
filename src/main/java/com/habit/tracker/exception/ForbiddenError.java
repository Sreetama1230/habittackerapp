package com.habit.tracker.exception;

public class ForbiddenError  extends RuntimeException{

	public ForbiddenError(String str){
		super(str);
	}
	public ForbiddenError(){
		super();
	}
}
