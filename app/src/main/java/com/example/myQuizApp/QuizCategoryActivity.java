package com.example.myQuizApp;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.myquizapp.R;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuizCategoryActivity extends ListActivity{

   private SQLiteDatabase db;
   private Cursor cursor;
   public ArrayList<QuizModel> quizzes = new ArrayList<QuizModel>();
   @Override
   protected void onCreate(Bundle savedInstanceState) {

       super.onCreate(savedInstanceState);

       Retrofit retrofit = new Retrofit.Builder()
               .baseUrl("http://192.168.1.114/php_rest_myQuizApp/api/").client(getHttpClient()).addConverterFactory(GsonConverterFactory.create()).build();
       JsonInterface service = retrofit.create(JsonInterface.class);
       Call<ArrayList<QuizModel>> call = service.getQuizzes();
       call.enqueue(new Callback<ArrayList<QuizModel>>() {
           @Override
           public void onResponse(Call<ArrayList<QuizModel>> call, Response<ArrayList<QuizModel>> response) {
               Log.e("onResponse","onResponse called");
               if (!response.isSuccessful()) {
                   QuizModel err = new QuizModel(99, "Something went wrong... code:", Integer.toString(response.code()));
                   quizzes.add(err);
               } else {
                   quizzes = response.body();
               }
           }
           @Override
           public void onFailure(Call<ArrayList<QuizModel>> call, Throwable t) {
               Log.e("onFailure","onFailure called");
               QuizModel err = new QuizModel(99, "Something went wrong... code:", t.getMessage());
               quizzes.add(err);
           }
       });

       QuizAdapter adapter = new QuizAdapter(this, quizzes);
       ListView listView = (ListView) findViewById(R.id.quizLV);
       listView.setAdapter(adapter);

       adapter.addAll(quizzes);

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
