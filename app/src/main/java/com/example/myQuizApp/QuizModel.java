package com.example.myQuizApp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class QuizModel implements Serializable {

    //model to build java objects from json returned from server.

    @SerializedName("_id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String desc;

    public int getId() {return id;}
    public String getName() {return name;}
    public String getDesc() {return desc;}
}

