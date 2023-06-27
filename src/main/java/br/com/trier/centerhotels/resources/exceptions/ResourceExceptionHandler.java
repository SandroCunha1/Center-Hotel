package br.com.trier.centerhotels.resources.exceptions;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(br.com.trier.centerhotels.services.exceptions.ObjectNotFound.class)
	public ResponseEntity<StandardError> obectNotFound (br.com.trier.centerhotels.services.exceptions.ObjectNotFound ex, HttpServletRequest request){
		StandardError error = new StandardError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	@ExceptionHandler(br.com.trier.centerhotels.services.exceptions.IntegrityViolation.class)
	public ResponseEntity<StandardError> integrityViolation (br.com.trier.centerhotels.services.exceptions.IntegrityViolation ex, HttpServletRequest request){
		StandardError error = new StandardError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}
