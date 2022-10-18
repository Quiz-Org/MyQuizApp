package com.example.myQuizApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.myquizapp.R;


public class TopLevelActivity extends Activity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_top_level_activity);

       // declare new instance of OnItemClickListener and override onItemClick method
       AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener(){

           @Override
           public void onItemClick(AdapterView<?> listView, View itemView, int position, long id) {

               if(position == 0) {

                   Intent intent = new Intent(TopLevelActivity.this, QuizCategoryActivity.class);
                   startActivity(intent);

               }

           }

       };

       ListView listView = (ListView) findViewById(R.id.list_options);

       listView.setOnItemClickListener(itemClickListener);

   }

}
