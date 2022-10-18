package com.example.myQuizApp;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.view.View;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class QuizCategoryActivity extends ListActivity{

   private SQLiteDatabase db;
   private Cursor cursor;

   @Override
   protected void onCreate(Bundle savedInstanceState){

       super.onCreate(savedInstanceState);
       ListView listQuizzes = getListView();

       try {

           SQLiteOpenHelper databaseHelper = new DatabaseHelper(this);
           db = databaseHelper.getReadableDatabase();

           cursor = db.query("QUIZ", new String[]{"_id", "NAME"}, null, null, null, null, null);

           CursorAdapter listAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, new String[]{"NAME"}, new int[]{android.R.id.text1}, 0);

           listQuizzes.setAdapter(listAdapter);

       } catch (SQLiteException e){

           Toast toast = Toast.makeText(this, "Database unavailable" + e.getMessage(), Toast.LENGTH_LONG);
           toast.show();

       }

   }
   @Override
   public void onDestroy(){

       super.onDestroy();
       cursor.close();
       db.close();

   }

   @Override
   protected void onListItemClick(ListView listView, View listQuizes, int position ,long id  ) {

            Intent intent = new Intent(QuizCategoryActivity.this, QuestionsActivity.class);
            intent.putExtra(QuestionsActivity.EXTRA_QUIZ_ID,  (int)id);
            startActivity(intent);


   }
}
