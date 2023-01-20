package com.example.myQuizApp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myQuizApp.Models.QuizModel;
import com.example.myquizapp.R;

import java.util.List;

public class QuizAdapter extends ArrayAdapter<QuizModel>{

    //adapter to inflate ListView for QuizCatagoryActivity from list of QuizModels
    public QuizAdapter(Context context, List<QuizModel> quiz){
        super(context,0,quiz);
    }

    //inflate view using quiz_item
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        QuizModel quiz = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.quiz_item,parent,false);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.name);
        TextView tvDesc = (TextView) convertView.findViewById(R.id.description);

        tvName.setText(quiz.getName());
        tvDesc.setText(quiz.getDesc());

        return convertView;
    }
}
