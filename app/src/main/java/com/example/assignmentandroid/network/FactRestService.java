package com.example.assignmentandroid.network;

import com.example.assignmentandroid.model.Country;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by Belal on 10/2/2017.
 */

public interface FactRestService {

    final String SERVICE_ENDPOINT = "https://dl.dropboxusercontent.com/s/2iodh4vg0eortkl/facts/";

    /**
     * Use Retrofit to get JSON from URL, then parse it.
     * @return Observable
     */
    @GET("facts/")
    Single<Country> getCountry();
}
