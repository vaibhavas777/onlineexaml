package com.jbk.onlineexam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

	@RequestMapping("adminhome")
	public ModelAndView getAdminHomePage() {
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin");
		
		return modelAndView;
		
	}
}
