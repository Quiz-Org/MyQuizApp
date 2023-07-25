package com.example.myQuizApp;

import com.example.myQuizApp.Models.QABundleModel;
import com.example.myQuizApp.Models.QuizModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RESTInterface {
    @GET("quiz/all")
    Call<ArrayList<QuizModel>> getQuizzes();

    @GET("quiz/QA?")
    Call<ArrayList<QABundleModel>> getQuestions(@Query("quizId") int quizId);

}
