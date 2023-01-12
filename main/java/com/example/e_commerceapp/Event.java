package com.example.e_commerceapp;

import android.graphics.Bitmap;

public class Event {
    String key = "";
    String name = "";
    String price = "";
    String description = "";
    Bitmap sImages;


    public Event(String key, String name, String price, String description,Bitmap sImages){
        this.key = key;
        this.name = name;
        this.price = price;
        this.description = description;
        this.sImages = sImages;
    }
}
