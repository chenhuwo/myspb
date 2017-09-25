package com.chw.spb.config;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
/**
 * 
 * @author chw
 *
 */
@Controller
@RequestMapping("/error")
public class CustomerErrController extends AbstractErrorController {
	
	private static final Logger log = LoggerFactory.getLogger(CustomerErrController.class);
	
	private static final String ERROR_PATH = "/error";

	public CustomerErrController(ErrorAttributes errorAttributes) {
		super(errorAttributes);
		
	}

	@Override
	public String getErrorPath() {
		
		return ERROR_PATH;
	}
	@RequestMapping(produces = "text/html")
	public ModelAndView errorHtml(HttpServletRequest request,
			HttpServletResponse response) {
		HttpStatus status = getStatus(request);
		Map<String, Object> model = Collections.unmodifiableMap(getErrorAttributes(
				request, false));
		response.setStatus(status.value());
		log.error("请求异常：{}",model);
		return new ModelAndView("/error/error.html",model);
	}

	@RequestMapping
	@ResponseBody
	public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
		Map<String, Object> body = getErrorAttributes(request,
				false);
		HttpStatus status = getStatus(request);
		ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<Map<String, Object>>(body, status);
		log.error("请求异常：{}",responseEntity);
		return responseEntity;
	}
	
	
	
	
	
}
