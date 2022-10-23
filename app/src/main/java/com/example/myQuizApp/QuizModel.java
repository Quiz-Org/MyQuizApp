package com.example.myQuizApp;

import com.google.gson.annotations.SerializedName;

public class QuizModel {

    @SerializedName("_id")
    int id;

    @SerializedName("Name")
    String name;

    @SerializedName("Desc")
    String desc;

    public int get_id(){return id;}
    public String getName(){return name;}
    public String getDesc(){return desc;}

}
