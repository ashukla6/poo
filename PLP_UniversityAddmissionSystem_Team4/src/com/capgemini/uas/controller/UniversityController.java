package com.capgemini.uas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UniversityController {
	
	@RequestMapping("/home.do")
	public ModelAndView goToHome(){
		ModelAndView mAndV = new ModelAndView();
		mAndV.setViewName("Welcome");
		return mAndV;
		
	}
}
