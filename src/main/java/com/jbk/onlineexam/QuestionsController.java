package com.jbk.onlineexam;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jbk.users.Answer;
import com.jbk.users.Questions;
import com.jbk.users.Score;

@Controller
public class QuestionsController {

	@Autowired
	SessionFactory factory;

	@RequestMapping("getQuestions")
	public ModelAndView getQuestions(String subject, HttpServletRequest request) {
		//here we are fetching questions from database
		Session session = factory.openSession();
		org.hibernate.Query<Questions> query = session.createQuery("from Questions where subject=:subject");
		query.setParameter("subject", subject);

		List<Questions> listOfQuestions = query.list();
		System.out.println(listOfQuestions);

		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("listOfQuestions", listOfQuestions);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("questions");
		return modelAndView;
	}

	@RequestMapping("startExam")
	public ModelAndView startExam(String selectedSubject, HttpServletRequest request) {
		// here we are showing questions
		if(!selectedSubject.equals("actionNoRequire"))
		{
			HttpSession httpSession = request.getSession();
			List<Questions> listOfQuestions = (List<Questions>) httpSession.getAttribute("listOfQuestions");

			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("questionsnavigation");
			modelAndView.addObject("question", listOfQuestions.get(0));

			httpSession.setAttribute("score", 0);
			httpSession.setAttribute("qno", 0);
			httpSession.setAttribute("timeremaining", 3);
			
			httpSession.setAttribute("submittedAnswer", new HashMap<Integer, Answer>());
			
			//selecting subject to display score (username,subject,score) in database
			httpSession.setAttribute("subject", selectedSubject);
			return modelAndView;
		}
		else
		{
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.setViewName("questions");
			modelAndView.addObject("msg", "Do not select this option");
			return modelAndView;
		}

	}
	
	@RequestMapping("storeResponse")
	public void storeResponse(HttpServletRequest request,Answer answer) {
		//System.out.println("before getting correct answer from list :"+answer);
		//taking correct answer form here above
		HttpSession httpSession = request.getSession(); 
		List<Questions> listOfQuestions = (List<Questions>) httpSession.getAttribute("listOfQuestions");
		String correctAnswer= listOfQuestions.get(answer.getQno()-1).getAnswer();
		answer.setCorrectAnswer(correctAnswer);
		//System.out.println("after getting correct answer from list :"+answer);
		
		//1.retrieve hashmap object form session
		HashMap<Integer, Answer> hashmapObject = (HashMap<Integer,Answer>) httpSession.getAttribute("submittedAnswer");
		//2. update retrieved hashmap object
		hashmapObject.put(answer.getQno(), answer);
		//3. Adding this updated hashmap object in session using setAttribute
		httpSession.setAttribute("submittedAnswer", hashmapObject);
	
		System.out.println(hashmapObject);
		
		
	}
	
	@RequestMapping("next")
	public ModelAndView next(HttpServletRequest request) {
		HttpSession httpSession = request.getSession(); 
		List<Questions> listOfQuestions = (List<Questions>) httpSession.getAttribute("listOfQuestions");
		httpSession.setAttribute("qno", (Integer)httpSession.getAttribute("qno")+1);

		ModelAndView modelAndView = new ModelAndView();
		if((Integer) httpSession.getAttribute("qno")< listOfQuestions.size()) {
			Questions questions = listOfQuestions.get((Integer) httpSession.getAttribute("qno"));
			modelAndView.setViewName("questionsnavigation");
			modelAndView.addObject("question", questions);
		}
		else {
			modelAndView.setViewName("questionsnavigation");
			modelAndView.addObject("message", "questions over......");
		}
		return modelAndView;

	}
	
	@RequestMapping("previous")
	public ModelAndView previous(HttpServletRequest request) {
		HttpSession httpSession = request.getSession(); 
		List<Questions> listOfQuestions = (List<Questions>) httpSession.getAttribute("listOfQuestions");
		httpSession.setAttribute("qno", (Integer)httpSession.getAttribute("qno")-1);

		ModelAndView modelAndView = new ModelAndView();
		if((Integer) httpSession.getAttribute("qno")>= 0) {
			Questions questions = listOfQuestions.get((Integer) httpSession.getAttribute("qno"));
			modelAndView.setViewName("questionsnavigation");
			modelAndView.addObject("question", questions);
		}
		else {
			modelAndView.setViewName("questionsnavigation");
			modelAndView.addObject("message2", "click on next button");
		}
		return modelAndView;

	}
	

	@RequestMapping("endexam")
	public ModelAndView endExam(HttpServletRequest request) {
		System.out.println("Inside end exam");
		HttpSession httpSession = request.getSession();
		HashMap<Integer, Answer> hashmapObject = (HashMap<Integer,Answer>) httpSession.getAttribute("submittedAnswer");
		System.out.println(hashmapObject);
		
		ModelAndView modelAndView = null;
		if(hashmapObject != null) 
		{
			Collection<Answer> answerscollection = hashmapObject.values();
			
			for(Answer answer : answerscollection)
			{
				if(answer.submittedAnswer.equals(answer.correctAnswer))
				{
					httpSession.setAttribute("score", (Integer)httpSession.getAttribute("score")+1);
				}
					
			}
			// store score in database
			
			Session hibernateSession = factory.openSession();
			Transaction tr = hibernateSession.beginTransaction();
			
			Score score = new Score();
			score.setUsername((String) httpSession.getAttribute("username"));
			score.setSubject((String) httpSession.getAttribute("subject"));
			score.setMarks((Integer) httpSession.getAttribute("score"));
			
			hibernateSession.save(score);
			tr.commit();
			
			modelAndView = new ModelAndView();
			modelAndView.setViewName("score");
			modelAndView.addObject("score", httpSession.getAttribute("score"));
			modelAndView.addObject("submittedAnswer", httpSession.getAttribute("submittedAnswer"));
			httpSession.invalidate();
			
		}
		
		else {
			modelAndView = new ModelAndView();
			modelAndView.setViewName("login");
			modelAndView.addObject("endexam", "Thank-You, Exam is finished....");
		}
		
		return modelAndView;

	}
	@RequestMapping("loginpage")
	public String goToLogin()
	{
		return "login";
		
	}
	
	@RequestMapping("score")
	String score(HttpServletRequest request)
	{
		System.out.println("inside end exam");
		HttpSession httpSession = request.getSession();
		HashMap<Integer,Answer> hashmapobject = (HashMap<Integer,Answer>) httpSession.getAttribute("submittedDetails");
		
		System.out.println(hashmapobject);
	
		
if(hashmapobject!=null) {
		Collection<Answer> answers=hashmapobject.values();
		
		for(Answer answer:answers)
		{
			if(answer.submittedAnswer.equals(answer.correctAnswer))
			{
				httpSession.setAttribute("score", (Integer)httpSession.getAttribute("score") + 1);
							
			}
		}
		
		// store score in database
		
		Session hibernateSession = factory.openSession();
		
		Transaction tx = hibernateSession.beginTransaction();
		
						Score  score = new Score();
						
						score.setUsername((String)httpSession.getAttribute("username"));
						score.setSubject((String)httpSession.getAttribute("subject"));
						score.setMarks((Integer)httpSession.getAttribute("score"));
		
						hibernateSession.save(score);
				
		tx.commit();
	
		
		
		request.setAttribute("score",httpSession.getAttribute("score"));
		request.setAttribute("submittedDetails",httpSession.getAttribute("submittedDetails"));
		
			
		
		httpSession.invalidate();
}


		
		return "score";
	}

}
