package com.example.myQuizApp;

import com.google.gson.annotations.SerializedName;

public class QuizModel {

    @SerializedName("_id")
    public int id;

    @SerializedName("Name")
    public String name;

    @SerializedName("Desc")
    public String desc;

    public QuizModel(int id,String name, String desc){this.id = id;this.name=name;this.desc=desc;}
    public QuizModel() {
        ;
    }

    public int get_id(){return id;}
    public String getName(){return name;}
    public String getDesc(){return desc;}

}
