package com.example.assignmentandroid.model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Created by Tulasa on 1/12/19.
 */


public class FactsRepository {

    private FactDao factDao;
    private LiveData<List<Fact>> allFacts;

    public FactsRepository(Application application) {
        FactRoomDataBase db = FactRoomDataBase.getInstance(application);
        factDao = db.factDao();
        allFacts = factDao.getAllFacts();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Fact>> getmAllQuestions() {
        return allFacts;
    }

    // You must call this on a non-UI thread or your app will crash.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    public void insert (Fact word) {
        new insertAsyncTask(factDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Fact, Void, Void> {

        private FactDao mAsyncTaskDao;

        insertAsyncTask(FactDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Fact... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
