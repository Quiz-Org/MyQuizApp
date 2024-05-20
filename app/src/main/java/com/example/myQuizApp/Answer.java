package com.example.myQuizApp;

import com.example.myQuizApp.Models.AnswersModel;

public class Answer {

	//Again these are variables that the class needs to store. Private so they are harder to accidentally change.
	private final int answerID;
	private final String answerText;
	private final Boolean correct;

	public Answer(AnswersModel answer) {
		this.answerID = answer.getId();
		this.answerText = answer.getAnswerText();
		this.correct = answer.getCorrect();

    }

    //Notice that this class has no setter methods. Once an Answer object is created, i don't need any of its data to be changed.
	//Using private variables, and not making any setter methods allows me to make sure this never happens by accident.
	public String getAnswerText() {
		return answerText;
	}

	public int getAnswerID() {return answerID; }

	public boolean getCorrect(){return correct;}

}
