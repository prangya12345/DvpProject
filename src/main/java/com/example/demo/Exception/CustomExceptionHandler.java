package com.example.demo.Exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.gargoylesoftware.htmlunit.WebRequest;

//import com.example.demo.exception.RecordNotFoundException;
@ControllerAdvice
public class CustomExceptionHandler extends  ResponseEntityExceptionHandler{
	
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<Object> findCustomerDetails(RecordNotFoundException rnf,WebRequest webRequest){
		List<String> details=new ArrayList<>();
		details.add(rnf.getLocalizedMessage());
		return new ResponseEntity("record not found error", HttpStatus.NOT_FOUND);
		
	}
	

}
