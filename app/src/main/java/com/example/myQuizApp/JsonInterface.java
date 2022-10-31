package com.example.myQuizApp;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonInterface {
    @GET("quiz/read.php")
    Call<ResponseBody> getQuizzes();
}
