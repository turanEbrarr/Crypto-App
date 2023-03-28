package com.turan.cryptoapp.service;

import com.turan.cryptoapp.model.Money;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APi {

    // bana veri gelecek diyorum ve nasıl geleceğini belirtiyorum
    @GET("/current.json or /current.xml")
    //Call<List<Money>> getData();
    Observable<List<Money>> getData();




}
