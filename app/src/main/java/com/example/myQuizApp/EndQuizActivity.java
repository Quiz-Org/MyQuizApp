package com.example.myQuizApp;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;
import com.example.myquizapp.R;

import java.util.ArrayList;

public class EndQuizActivity extends Activity {

   private ArrayList<Question> questions = new ArrayList<Question>();
   private int correctAnswers;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_end_quiz);

       correctAnswers = 0;
       questions = GlobalData.passQuestions;

       //as static in a diff class will not be caught by java garbage collection unless made null
       GlobalData.passQuestions = null;

       checkAnswers();
       setScreen();

   }

   private void checkAnswers(){

       for (Question question: questions) {

          if(question.checkCorrect()){

              correctAnswers += 1;

          }

       }

   }

   private void setScreen(){

       TextView scoreView = (TextView) findViewById(R.id.scoreBox);
       scoreView.setText(String.valueOf(correctAnswers));

       TextView questNumView = (TextView) findViewById(R.id.questionNumber);
       questNumView.setText(String.valueOf(questions.size()));
   }

}
