package com.jbk.onlineexam;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jbk.users.Mail;

@Controller
public class SendMailController {
    
	@RequestMapping("sendMail")
	public void sendMail( Mail mail, HttpServletRequest request) 
	{
		String to = request.getParameter("to");
		String subject = request.getParameter("subject");
		String msg = request.getParameter("msg");
		
		Mailer.send(to, subject, msg);
		System.out.println("Mail has been sent successfully.....");
		System.out.close();
	}
}
