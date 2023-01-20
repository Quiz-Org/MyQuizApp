package com.example.myQuizApp.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.myQuizApp.GlobalData;
import com.example.myQuizApp.Models.QABundleModel;
import com.example.myQuizApp.Models.QuestionModel;
import com.example.myQuizApp.Question;
import com.example.myQuizApp.RESTInterface;
import com.example.myquizapp.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuestionsActivity extends Activity {

    public static final String EXTRA_QUIZ_ID = "quizID";
    private int questNumCurrent;
    private int questNumTot;
    private ArrayList<Question> questions;
    private int quizID;
    private final int[] answerIDs = new int[4];

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_activity);
        //* get quiz id from intent
        quizID = (Integer) getIntent().getExtras().get(EXTRA_QUIZ_ID) + 1;
        questNumCurrent = -1;

        populateList(quizID);

    }
    public void setQuestions(ArrayList<QuestionModel> qestionsIn){

        System.out.println();
        //this.questions = qestionsIn;

    }

    private void populateList(Integer quizID){

        String url = "http://192.168.1.114:8080/";
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        //setup retrofit with Gson and server url
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        //sets the request retrofit will send, and java objects to be created from response.
        RESTInterface service = retrofit.create(RESTInterface.class);
        Call<ArrayList<QABundleModel>> call = service.getQuestions(quizID);

        //send request with callback. Log failures or incorrect returns, if correct send return to setupView
        call.enqueue(new Callback<ArrayList<QABundleModel>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<QABundleModel>> call, @NonNull Response<ArrayList<QABundleModel>> response) {
                System.out.println();

                if (!response.isSuccessful()) {
                    Log.e("Something went wrong... code:", Integer.toString(response.code()));
                } else {
                    //get ArrayList of QuizModels created by Gson from response body, past to setupView
                    assert response.body() != null;
                    questions = new ArrayList<>();
                    for(QABundleModel QA : response.body()){questions.add(new Question(QA.getQuestion(),QA.getAnswers()));}
                    questNumTot = questions.size();
                    refreshView();
                }
            }
            @Override
            public void onFailure(@NonNull Call<ArrayList<QABundleModel>> call, @NonNull Throwable t) {
                Log.e("Something went wrong... code:", t.getMessage());
            }

        });
    }

    private void refreshView() {

        questNumCurrent++;

        RadioGroup answerGroup = findViewById(R.id.answerGroup);

        answerGroup.clearCheck();

        TextView questionBox = findViewById(R.id.questionBox);
        questionBox.setText(questions.get(questNumCurrent).getQuestionText());

        boolean[] empty = new boolean[4];

        for (int i = 0; i < empty.length; i++) {

            boolean placed = false;

            while (!placed) {

                Random rand = new Random();

                int n = rand.nextInt(4) + 1;

                switch (n) {
                    case 1:

                        if (!empty[0]) {

                            RadioButton answerButton = findViewById(R.id.answerButton1);
                            answerButton.setText(questions.get(questNumCurrent).getPossAnswer(i).getAnswerText());

                            empty[0] = true;
                            placed = true;

                            answerIDs[0] = questions.get(questNumCurrent).getPossAnswer(i).getAnswerID();

                        }
                        break;

                    case 2:

                        if (!empty[1]) {

                            RadioButton answerButton = findViewById(R.id.answerButton2);
                            answerButton.setText(questions.get(questNumCurrent).getPossAnswer(i).getAnswerText());

                            empty[1] = true;
                            placed = true;

                            answerIDs[1] = questions.get(questNumCurrent).getPossAnswer(i).getAnswerID();

                        }
                        break;

                    case 3:

                        if (!empty[2]) {

                            RadioButton answerButton = findViewById(R.id.answerButton3);
                            answerButton.setText(questions.get(questNumCurrent).getPossAnswer(i).getAnswerText());

                            empty[2] = true;
                            placed = true;

                            answerIDs[2] = questions.get(questNumCurrent).getPossAnswer(i).getAnswerID();

                        }
                        break;
                    case 4:

                        if (!empty[3]) {

                            RadioButton answerButton = findViewById(R.id.answerButton4);
                            answerButton.setText(questions.get(questNumCurrent).getPossAnswer(i).getAnswerText());

                            empty[3] = true;
                            placed = true;

                            answerIDs[3] = questions.get(questNumCurrent).getPossAnswer(i).getAnswerID();

                        }
                        break;
                }

            }

        }

    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        int id = view.getId();
        if (id == R.id.answerButton1) {
            if (checked)
                questions.get(questNumCurrent).setGivenAnswer(answerIDs[0]);
        } else if (id == R.id.answerButton2) {
            if (checked)
                questions.get(questNumCurrent).setGivenAnswer(answerIDs[1]);
        } else if (id == R.id.answerButton3) {
            if (checked)
                questions.get(questNumCurrent).setGivenAnswer(answerIDs[2]);
        } else if (id == R.id.answerButton4) {
            if (checked)
                questions.get(questNumCurrent).setGivenAnswer(answerIDs[3]);
        }

    }

    public void onNextButtonClicked(View view) {


        if (questNumCurrent < questNumTot - 1) {

            refreshView();

        } else {


            GlobalData.passQuestions = questions;

            endQuiz();

        }

    }

    private void endQuiz() {

        Intent intent = new Intent(QuestionsActivity.this, EndQuizActivity.class);
        startActivity(intent);

    }

}
