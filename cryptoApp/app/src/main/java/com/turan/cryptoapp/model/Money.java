package com.turan.cryptoapp.model;

import com.google.gson.annotations.SerializedName;

public class Money {
    @SerializedName("note")
    public String name;

    @SerializedName("event")
    public  String price;

}
