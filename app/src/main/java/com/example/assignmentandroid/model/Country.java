package com.example.assignmentandroid.model;

import java.util.List;

public class Country {
    private String title;
    List<Fact> rows;

    public List<Fact> getRows() {
        return rows;
    }

    public String getTitle() {
        return title;
    }
}
