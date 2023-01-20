package com.example.myQuizApp;

import com.example.myQuizApp.Models.AnswersModel;
import com.example.myQuizApp.Models.QuestionModel;

import java.util.ArrayList;

public class Question {

	//Again these are variables that the class needs to store. Private so they are harder to accidentally change.
	private final int questionID;
	private final String questionText;
	private int givenAnswer;

	//This variable is very important. It stores the range of answers that can be given to a question.
	//Notice that it is of type answer, this shows the massive potential of an object oriented approach. Firstly this means it can store many variables together in a neat, easily accessible format.
	//But Answer is a fairly basic class. If this was to be done with a more complicated class, with many methods and variables, any number of complex problems can be solved fairly easily.
	private final ArrayList<Answer> possAnswers;
	
	
	//Again, a simple constructor.
	public Question(int questionID,String questionText){
		this.questionID = questionID;
		this.questionText = questionText;
		this.possAnswers = new ArrayList<>();
	}
	public Question(QuestionModel quizIn, ArrayList<AnswersModel> answers) {

		this.questionID = quizIn.getId();
		this.questionText = quizIn.getQuestionText();
		this.possAnswers = new ArrayList<>();
		for(AnswersModel answer: answers){
			this.possAnswers.add(new Answer(answer));
		}

	}

		//Again, my variables are private, so i need methods if i want to change them from outside the class. Notice how i have setter methods this time.
	public void setGivenAnswer(int givenAnswerIn){givenAnswer = givenAnswerIn;}
	public int getQuestionID() {return questionID;}
	public String getQuestionText(){return questionText;}
	public Answer getPossAnswer(int i){return possAnswers.get(i);}
	public int getGivenAnswer(){return givenAnswer;}

	
	
	//This method checks if the user has given the correct answer to a question. In the real app it is called by a UI element.
	public boolean checkCorrect(){


		for( Answer answer: possAnswers) {

			//notice here how i use the .getAnswerID and .getCorrect methods of the answer. Without these variables wrapped up in my own class storing this data for every question would be much messier,
			//and messier means more chances to get things wrong, and harder to read code!
			if (answer.getAnswerID() == getGivenAnswer()) {

                return answer.getCorrect();

			}
		}

		return false;

	}
	
}

