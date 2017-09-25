package com.chw.spb.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author chw
 *
 */
//@ControllerAdvice
public class ExceptionHandlerAdvice {
	
	@ExceptionHandler
	public ModelAndView exception(Exception e,WebRequest req) {
		ModelAndView model = new ModelAndView();
		
		return model;
	}

}
