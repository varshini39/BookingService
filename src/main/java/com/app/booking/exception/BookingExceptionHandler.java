package com.app.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookingExceptionHandler {
	
	// Handling general exceptions
	@ExceptionHandler
	public ResponseEntity<BookingErrorResponse> handleException(Exception exc) {
		BookingErrorResponse error = new BookingErrorResponse();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	// Handles ID not found exception
	@ExceptionHandler
	public ResponseEntity<BookingErrorResponse> handleException(IdNotFoundException exc) {
		BookingErrorResponse error = new BookingErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
}
