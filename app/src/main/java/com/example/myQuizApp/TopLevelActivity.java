package com.example.myQuizApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.myquizapp.R;


public class TopLevelActivity extends Activity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_top_level_activity);

       // declare new instance of OnItemClickListener and override onItemClick method
       AdapterView.OnItemClickListener itemClickListener = (listView, itemView, position, id) -> {

           if(position == 0) {

               Intent intent = new Intent(TopLevelActivity.this, QuizCategoryActivity.class);
               startActivity(intent);

           }

       };

       ListView listView = findViewById(R.id.list_options);

       listView.setOnItemClickListener(itemClickListener);

   }

}
