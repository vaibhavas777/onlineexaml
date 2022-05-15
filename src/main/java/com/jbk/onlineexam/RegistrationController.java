package com.jbk.onlineexam;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.jbk.users.Users;
@Controller
public class RegistrationController {
	@Autowired
	SessionFactory factory;

	@RequestMapping("/")
	public String home() {
		System.out.println("inside home");
		return "login";
	}
	@RequestMapping("register")
	public ModelAndView register(Users user)
	{
		ModelAndView modelAndView = new ModelAndView();
		Session session = factory.openSession();
		try 
		{
			
			{
				Transaction tr = session.beginTransaction();
				session.save(user);
				tr.commit();

				modelAndView.setViewName("login");
				modelAndView.addObject("message","registration successfull...Please login...");
			}	
		}  catch(Exception e)
		{
			System.out.println("User already exsist.....");
			modelAndView.setViewName("register");
			modelAndView.addObject("errormessage","User already exsist, please choose different username....");
		}
		return modelAndView;



	}

}
