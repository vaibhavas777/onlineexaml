package com.jbk.onlineexam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jbk.users.LoginUser;
import com.jbk.users.Users;

@Controller
public class LoginController {

	@Autowired
	SessionFactory factory;

	@RequestMapping("showRegisterPage")
	public String showLoginPage()
	{
		return "register";
	}

	@RequestMapping("login")
	public ModelAndView login(LoginUser loginuser, HttpServletRequest request) 
	{
		Session session = factory.openSession();
		Users user = session.load(Users.class, loginuser.getUsername());
		ModelAndView modelAndView = new ModelAndView();
		try {
			if(loginuser.getUsername().equals("admin") && loginuser.getPassword().equals("admin123")) 
			{
				modelAndView.setViewName("admin");
				modelAndView.addObject("message", "Welcome "+ loginuser.getUsername());

			}
			else if(loginuser.getPassword().equals(user.getPassword()))
			{
				modelAndView.setViewName("questions");
				modelAndView.addObject("message", "Welcome "+ loginuser.getUsername());
			}

			else
			{
				modelAndView.setViewName("login");
				modelAndView.addObject("errormessage", "Invalid credential...");
			}

		}
		catch(Exception e)
		{
			System.out.println("Invalid credential , check username or password...");
			modelAndView.setViewName("login");
			modelAndView.addObject("errormessage", "Invalid credential...");
		}
		//selecting username to display score (username,subject,score) in database
		
		HttpSession sess = request.getSession();
		 sess.setAttribute("username", loginuser.getUsername());
		
		
		return modelAndView;
	}
}
