package com.example.myQuizApp.Models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class QuizModel implements Serializable {

    //model to build quiz objects from json returned from server.

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

