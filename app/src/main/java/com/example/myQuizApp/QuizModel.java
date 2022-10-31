package com.example.myQuizApp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class QuizModel implements Serializable {

    @SerializedName("_id")
    private int id;

    @SerializedName("Name")
    private String name;

    @SerializedName("Desc")
    private String desc;

    public QuizModel(int id,String name, String desc){this.id = id;this.name=name;this.desc=desc;}
    public QuizModel(){}

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int get_id(){return id;}
    public String getName(){return name;}
    public String getDesc(){return desc;}

}
