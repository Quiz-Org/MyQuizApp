package com.example.myQuizApp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EndPointInterface {

    @GET("quiz/read")
    Call<List<QuizModel>> getQuizzes();

}
