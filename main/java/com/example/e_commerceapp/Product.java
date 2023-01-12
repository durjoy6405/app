package com.example.e_commerceapp;

public class Product {
    String key = "";
    String name = "";
    String price = "";
    String sImage = "";
    String description = "";
    String quantity = "";

    public Product(String key, String name, String price, String sImage,String quanity,String description){
        this.key = key;
        this.name = name;
        this.price = price;
        this.sImage = sImage;
        this.quantity = quantity;
        this.description = description;

    }
}
