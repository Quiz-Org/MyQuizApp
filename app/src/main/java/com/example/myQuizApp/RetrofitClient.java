package com.example.myQuizApp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static final String BASE_URL = "http://localhost/php_rest_myQuizApp/api/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance(){
        if(retrofit == null){
           retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
