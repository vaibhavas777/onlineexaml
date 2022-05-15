package com.jbk.onlineexam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jbk.users.Questions;

@Controller
public class QuestionsManagementController {
	@Autowired
	SessionFactory factory;

	@RequestMapping("addQuestion")
	public ModelAndView addQuestion(Questions questions)
	{
		Session session = factory.openSession();
		Transaction tr = session.beginTransaction();
		session.save(questions);
		tr.commit();

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("questionsmanagement");
		modelAndView.addObject("quemessage", "Question added successfully....");
		return modelAndView;
	}

}
