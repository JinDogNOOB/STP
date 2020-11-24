package com.example.demo.board.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@RequestMapping("/error")
@Controller
public class ErrorController{
	
	@ExceptionHandler(Exception.class)
	public ModelAndView LoginAuthError(HttpServletRequest request, Exception exception) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", exception);
		mav.addObject("url", request.getRequestURL());
		
		
		mav.setViewName("error");
		return mav;
	}

}
