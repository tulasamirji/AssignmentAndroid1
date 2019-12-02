package com.example.assignmentandroid.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
@Entity(tableName = "Fact")
public class Fact {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;
    @SerializedName("imageHref")
    private String imageHref;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageHref() {
        return imageHref;
    }


    public Fact(String title, String description, String imageHref) {
        this.title = title;
        this.description = description;
        this.imageHref = imageHref;
    }
}
