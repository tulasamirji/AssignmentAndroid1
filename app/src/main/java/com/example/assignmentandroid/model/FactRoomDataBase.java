package com.example.assignmentandroid.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Fact.class}, version = 1 ,exportSchema = false)
public abstract class FactRoomDataBase extends RoomDatabase {

    private static FactRoomDataBase INSTANCE;

    public abstract FactDao factDao();

    private static final Object sLock = new Object();

    public static FactRoomDataBase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        FactRoomDataBase.class, "Facts.db")
                        .allowMainThreadQueries()
                        .addCallback(sRoomDatabaseCallback)
                        .build();
            }
            return INSTANCE;
        }
    }
    /**
     * Override the onOpen method to populate the database.
     * For this sample, we clear the database every time it is created or opened.
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
        }
    };
}