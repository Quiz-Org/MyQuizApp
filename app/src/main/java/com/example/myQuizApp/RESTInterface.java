package com.example.myQuizApp;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RESTInterface {
    @GET("quiz/read.php")
    Call<ArrayList<QuizModel>> getQuizzes();
}
