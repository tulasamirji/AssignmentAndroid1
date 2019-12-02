package com.example.assignmentandroid.viewmodel;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.assignmentandroid.model.FactsRepository;
import com.example.assignmentandroid.model.Country;
import com.example.assignmentandroid.model.Fact;
import com.example.assignmentandroid.network.FactRestService;
import com.example.assignmentandroid.network.RetrofitClientInstance;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class FactViewModel extends AndroidViewModel {

    private FactsRepository mRepository;

    private LiveData<List<Fact>> mAllQuestions;
    private MutableLiveData<List<Fact>> facList;
    //private MutableLiveData<List<Fact>> facList;


    public FactViewModel(Application application) {
        super(application);
        mRepository = new FactsRepository(application);
        mAllQuestions = mRepository.getmAllQuestions();
    }

    public LiveData<List<Fact>> getAllQuestions() { return mAllQuestions; }

    public void insert(Fact word) { mRepository.insert(word); }
    //this is the data that we will fetch asynchronously 


    //we will call this method to get the data
    public LiveData<List<Fact>> getFacts() {
        //if the list is null 
        if (facList == null) {
            facList =new MutableLiveData<>();
            //we will load it asynchronously from server in this method
            loadHeroes();
        }
        //finally we will return the list
        return facList;
    }

    @SuppressLint("CheckResult")
    private void loadHeroes() {

        /*Create handle for the RetrofitInstance interface*/
        FactRestService factRestService = RetrofitClientInstance.getRetrofitInstance().create(FactRestService.class);
        factRestService.getCountry()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Country>() {
                    @Override
                    public void onSuccess(Country country) {
                        Log.e("onSuccess","onSuccess");
                        List<Fact> fact = country.getRows();
                        facList.setValue(fact);
                        for(int i=0;i<fact.size();i++){
                            String title = fact.get(i).getTitle();
                            String desc = fact.get(i).getDescription();
                            String image = fact.get(i).getImageHref();

                            Fact fact1 = new Fact(title,desc,image);
                            insert(fact1);
                        }
                        Log.e("onSuccess",country.getTitle());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("onError",e.getMessage());
                    }
                });
    }
}
