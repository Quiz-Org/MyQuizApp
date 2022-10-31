package com.example.myQuizApp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuizCategoryActivity extends ListActivity{

   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);

       Gson gson = new GsonBuilder().setLenient().create();

       Retrofit retrofit = new Retrofit.Builder()
               .baseUrl("http://192.168.1.114/php_rest_myQuizApp/api/").client(getHttpClient()).addConverterFactory(GsonConverterFactory.create(gson)).build();
       JsonInterface service = retrofit.create(JsonInterface.class);
       Call<ResponseBody> call = service.getQuizzes();
       call.enqueue(new Callback<ResponseBody>() {
           @Override
           public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
               String test = response.getClass().getCanonicalName();
               Log.e("onResponse",test);
               if (!response.isSuccessful()) {
                   QuizModel err = new QuizModel(99, "Something went wrong... code:", Integer.toString(response.code()));
                   List<QuizModel> quizzes = new ArrayList<QuizModel>();
                   quizzes.add(err);
               } else {

                   List<QuizModel> quizzes = new ArrayList<QuizModel>();
                   //quizzes.addAll();
                }
           }
           @Override
           public void onFailure(Call<ResponseBody> call, Throwable t) {
               Log.e("onFailure","onFailure called");
               QuizModel err = new QuizModel(99, "Something went wrong... code:", t.getMessage());
               List<QuizModel> quizzes = new ArrayList<QuizModel>();
               quizzes.add(err);
           }
       });

       System.out.println();

   }
    public static OkHttpClient getHttpClient() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);



        //TODO : remove logging interceptors as it is to be used for development purpose
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(300, TimeUnit.SECONDS)
                .readTimeout(300,TimeUnit.SECONDS).
                addInterceptor(logging).
                build();

        return client;
    }

   @Override
   protected void onListItemClick(ListView listView, View listQuizzes, int position ,long id  ) {

            Intent intent = new Intent(QuizCategoryActivity.this, QuestionsActivity.class);
            intent.putExtra(QuestionsActivity.EXTRA_QUIZ_ID,  (int)id);
            startActivity(intent);


   }
}
