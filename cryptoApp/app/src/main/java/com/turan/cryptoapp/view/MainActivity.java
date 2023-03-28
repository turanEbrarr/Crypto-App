package com.turan.cryptoapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.turan.cryptoapp.R;
import com.turan.cryptoapp.adapter.RecyclerViewAdapter;
import com.turan.cryptoapp.model.Money;
import com.turan.cryptoapp.service.APi;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ArrayList models;
    final private String BASE_URL = "http://api.weatherapi.com/v1";
    Retrofit retrofit;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;

    //rx java
    CompositeDisposable compositeDisposable;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Gson gson = new GsonBuilder().setLenient().create();
        //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json
        recyclerView=findViewById(R.id.recyclerView);
        retrofit= new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //rx java
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        loadData();

    }

    private void loadData() {

        final APi aPi = retrofit.create(APi.class);

        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(aPi.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::eleAl)
        );

        /*
        Call<List<Money>> call = aPi.getData();

        call.enqueue(new Callback<List<Money>>() {
            @Override
            public void onResponse(Call<List<Money>> call, Response<List<Money>> response) {
                if(response.isSuccessful()){
                    List<Money> responseList = response.body();
                    models = new ArrayList<>(responseList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerViewAdapter = new RecyclerViewAdapter(models);
                    recyclerView.setAdapter(recyclerViewAdapter);

                }

            }

            @Override
            public void onFailure(Call<List<Money>> call, Throwable t) {
                t.printStackTrace();
                System.out.println("hata");

            }
        });

         */
    }
    private void eleAl(List<Money> m){

        models = new ArrayList<>(m);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerViewAdapter = new RecyclerViewAdapter(models);
        recyclerView.setAdapter(recyclerViewAdapter);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}