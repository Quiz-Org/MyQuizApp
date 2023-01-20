package com.example.myQuizApp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class QuestionModel implements Serializable {

    @SerializedName("_id")
    @Expose
    private Integer id;

    @SerializedName("quizId")
    @Expose
    private Integer quizId;

    @SerializedName("questionText")
    @Expose
    private String questionText;

    public Integer getId() {return id;}
    public Integer getQuizId() {return quizId;}
    public String getQuestionText() {return questionText;}
}
