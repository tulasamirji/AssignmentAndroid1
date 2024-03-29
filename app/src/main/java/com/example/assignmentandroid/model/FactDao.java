package com.example.assignmentandroid.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FactDao {

    @Insert
    void insert(Fact fact);

    /**
     * @return
     */
    @Query("SELECT * FROM Fact")
    LiveData<List<Fact>> getAllFacts();
}
