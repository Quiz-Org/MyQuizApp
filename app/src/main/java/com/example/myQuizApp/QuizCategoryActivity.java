package com.example.myQuizApp;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizCategoryActivity extends ListActivity{

   private SQLiteDatabase db;
   private Cursor cursor;

   @Override
   protected void onCreate(Bundle savedInstanceState){

       super.onCreate(savedInstanceState);
       ListView listQuizzes = getListView();

       EndPointInterface service = RetrofitClient.getRetrofitInstance().create(EndPointInterface.class);
       Call<List<QuizModel>> call = service.getQuizzes();
       call.enqueue(new Callback<List<QuizModel>>() {
           @Override
           public void onResponse(Call<List<QuizModel>> call, Response<List<QuizModel>> response) {
               generateDataList(response.body());
           }

           @Override
           public void onFailure(Call<List<QuizModel>> call, Throwable t) {
               Toast.makeText(QuizCategoryActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
           }
       });
       System.out.println();

       /*try {

           SQLiteOpenHelper databaseHelper = new DatabaseHelper(this);
           db = databaseHelper.getReadableDatabase();

           cursor = db.query("QUIZ", new String[]{"_id", "NAME"}, null, null, null, null, null);

           CursorAdapter listAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, new String[]{"NAME"}, new int[]{android.R.id.text1}, 0);

           listQuizzes.setAdapter(listAdapter);

       } catch (SQLiteException e){

           Toast toast = Toast.makeText(this, "Database unavailable" + e.getMessage(), Toast.LENGTH_LONG);
           toast.show();

       } */

   }

    private void generateDataList(List<QuizModel> quizList) {


   }

    @Override
   public void onDestroy(){

       super.onDestroy();
       cursor.close();
       db.close();

   }

   @Override
   protected void onListItemClick(ListView listView, View listQuizzes, int position ,long id  ) {

            Intent intent = new Intent(QuizCategoryActivity.this, QuestionsActivity.class);
            intent.putExtra(QuestionsActivity.EXTRA_QUIZ_ID,  (int)id);
            startActivity(intent);


   }
}
