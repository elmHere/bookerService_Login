package pw.io.booker.aspect;

import org.h2.api.ErrorCode;

/// a custom exception
public class MyException extends RuntimeException{
	
	String message;
	
	public MyException (String message) {
		super();
		this.message = message;
	}

}
