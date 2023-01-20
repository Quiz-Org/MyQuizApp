package com.example.myQuizApp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class QABundleModel implements Serializable {

    @SerializedName("question")
    @Expose
    private QuestionModel question;

    @SerializedName("answers")
    @Expose
    private ArrayList<AnswersModel> answers;

    public QuestionModel getQuestion() {return question;}
    public ArrayList<AnswersModel> getAnswers() {return answers;}

}
