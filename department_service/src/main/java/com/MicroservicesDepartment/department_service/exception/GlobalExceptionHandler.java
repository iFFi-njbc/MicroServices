package com.MicroservicesDepartment.department_service.exception;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.swagger.annotations.ApiResponse;


@ControllerAdvice
public class GlobalExceptionHandler{
	
	
	//execute if there is an error in binding jason object into our model

/*	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<?> handleException1(DataIntegrityViolationException ex)
	{
		String msg = "Enter all the required feilds : " + ex.getMessage();
		return new ResponseEntity(msg, HttpStatus.FORBIDDEN);
	} */
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> handleException2(RuntimeException ex)
	{
		String msg = ex.getMessage();
		return new ResponseEntity(msg, HttpStatus.FORBIDDEN);
	} 
	
	@ExceptionHandler
	public ResponseEntity<ErrorDetails> handleBuisnessException(BuisnessException b)
	{
		ErrorDetails e = new ErrorDetails();
		e.setErrorCode(b.getErrorCode());
		e.setHttp(HttpStatus.BAD_REQUEST);
		e.setMessage(b.getErrorMessage());
		
		return new ResponseEntity<ErrorDetails>(e, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleExceptions(Exception e)
	{
		ErrorDetails ed = new ErrorDetails();
		ed.setHttp(HttpStatus.FORBIDDEN);
		ed.setMessage("Hello ----> " + e.getMessage());
		ed.setErrorCode("404");
		
		return new ResponseEntity<ErrorDetails>(ed, HttpStatus.BAD_REQUEST);
	}
	
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
    	Map<String, String> resp = new HashMap<>();
    	exception.getBindingResult().getAllErrors() //returns a list of errors
                .forEach((error) -> {
                	String fieldname = ((FieldError)error).getField();
                	String message = error.getDefaultMessage();
                	resp.put(fieldname, message);
                });
        
        return new ResponseEntity<Map<String, String>>(resp, HttpStatus.BAD_REQUEST);
    } 
	
}





















/*	@ExceptionHandler(BenifitsException.class)
public ResponseEntity<ErrorDetails> handleBenifitsExceptions(BenifitsException e)
{
	ErrorDetails ed = new ErrorDetails();
	ed.setHttp(HttpStatus.FORBIDDEN);
	ed.setMessage(e.getErrorMessage());
	ed.setErrorCode(e.getErrorCode());
	
	return new ResponseEntity<ErrorDetails>(ed, HttpStatus.BAD_REQUEST);
}

@ExceptionHandler(DepartmentException.class)
public ResponseEntity<ErrorDetails> handleDepartmentExceptions(DepartmentException e)
{
	ErrorDetails ed = new ErrorDetails();
	ed.setHttp(HttpStatus.FORBIDDEN);
	ed.setMessage(e.getErrorMessage());
	ed.setErrorCode(e.getErrorCode());
	
	return new ResponseEntity<ErrorDetails>(ed, HttpStatus.BAD_REQUEST);
} */