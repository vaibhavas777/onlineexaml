package com.jbk.users;

public class Answer {

	public int qno;
	public String question;
	public String correctAnswer;
	public String submittedAnswer;
	public int getQno() {
		return qno;
	}
	public void setQno(int qno) {
		this.qno = qno;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	public String getSubmittedAnswer() {
		return submittedAnswer;
	}
	public void setSubmittedAnswer(String submittedAnswer) {
		this.submittedAnswer = submittedAnswer;
	}
	@Override
	public String toString() {
		return "Answer [qno=" + qno + ", question=" + question + ", correctAnswer=" + correctAnswer
				+ ", submittedAnswer=" + submittedAnswer + "]";
	}
	
	
}
