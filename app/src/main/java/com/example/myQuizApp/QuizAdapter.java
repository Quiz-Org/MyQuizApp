package com.example.myQuizApp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myquizapp.R;

import java.util.ArrayList;

public class QuizAdapter extends ArrayAdapter<QuizModel>{

    public QuizAdapter(Context context, ArrayList<QuizModel> quiz){
        super(context,0,quiz);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        QuizModel quiz = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_quiz_category_activity,parent,false);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.name);
        TextView tvDesc = (TextView) convertView.findViewById(R.id.description);

        tvName.setText(quiz.getName());
        tvDesc.setText(quiz.getDesc());
        return convertView;
    }

}
