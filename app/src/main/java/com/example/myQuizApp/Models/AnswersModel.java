package com.example.myQuizApp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AnswersModel implements Serializable {

    @SerializedName("_id")
    @Expose
    private Integer id;

    @SerializedName("questionId")
    @Expose
    private Integer questionID;

    @SerializedName("correct")
    @Expose
    private Boolean correct;

    @SerializedName("answerText")
    @Expose
    private String answerText;

    public Integer getId() {return id;}
    public Integer getQuestionsID() {return questionID;}
    public String getAnswerText() {return answerText;}
    public Boolean getCorrect() {return correct;}
}
