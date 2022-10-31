package com.example.myQuizApp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import com.example.myquizapp.R;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuizCategoryActivity extends Activity {

    //setup activity & set layout, call populate method.
   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_quiz_category_activity);
       populateList();
   }

   /*Use Retrofit & Gson to GET list of quizzes from web server.
   * Get quiz/read.php will return a json list of quizes.
   * Retrofit sends the request, and Gson is used to create java objects from json return
   * */
   private void populateList(){

       String url = "http://192.168.1.114/php_rest_myQuizApp/api/";


       //setup retrofit with Gson and server url
       Retrofit retrofit = new Retrofit.Builder()
               .baseUrl(url)
               .addConverterFactory(GsonConverterFactory.create())
               .build();
       
       //sets the request retrofit will send, and java objects to be created from response.
       RESTInterface service = retrofit.create(RESTInterface.class);
       Call<ArrayList<QuizModel>> call = service.getQuizzes();
       
       //send request with callback. Log failures or incorrect returns, if correct send return to setupView
       call.enqueue(new Callback<ArrayList<QuizModel>>() {
           @Override
           public void onResponse(Call<ArrayList<QuizModel>> call, Response<ArrayList<QuizModel>> response) {
               if (!response.isSuccessful()) {
                   Log.e("Something went wrong... code:", Integer.toString(response.code()));
               } else {

                   //get ArrayList of QuizModels created by Gson from response body, past to setupView
                   ArrayList<QuizModel> quizzes = new ArrayList<QuizModel>();
                   quizzes.addAll(response.body());
                   setupView(quizzes);
               }
           }
           @Override
           public void onFailure(Call<ArrayList<QuizModel>> call, Throwable t) {
               Log.e("Something went wrong... code:", t.getMessage());
           }
       });
   }

   //method to setup view with adapter from list of QuizModels
   private void setupView(ArrayList<QuizModel> quizzes){

       QuizAdapter adapter = new QuizAdapter(QuizCategoryActivity.this, quizzes);
       ListView listView = (ListView) findViewById(R.id.quizLV);
       listView.setAdapter(adapter);

   }


}
