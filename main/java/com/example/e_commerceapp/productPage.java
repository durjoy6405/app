package com.example.e_commerceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class productPage extends AppCompatActivity {
    ListView scrollView;
    ArrayList<Product> products;
    CustomProductAdapter adapter;
    Button buynow, addcart;
    String key;
    String description;
    String sImage;
    String quantity;
    String price;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);
        scrollView = findViewById(R.id.scrollView);
        SharedPreferences sharedPref = this.getSharedPreferences("MySharedPref", MODE_PRIVATE);


// load Products from database if there is any
        loadData();
        addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor prefsEditor = sharedPref.edit();

                prefsEditor.putString("name",name.getText().toString());
                prefsEditor.putString("sImage", sImage.getText().toString());
                prefsEditor.putString("price",price.getText().toString());
                prefsEditor.putString("description",description.getText().toString());
                prefsEditor.putString("quantity",quantity.getText().toString());

                prefsEditor.apply();


            }
        });
    }
    private void loadData() {
        products = new ArrayList<>();
        KeyValueDB db = new KeyValueDB(this);
        Cursor rows = db.execute("SELECT * FROM key_value_pairs");
        if (rows.getCount() == 0) {
            return;
        }
        //events = new Event[rows.getCount()];
        while (rows.moveToNext()) {
            String key = rows.getString(0);
            String productData = rows.getString(1);
            String[] fieldValues = productData.split("---");

            String name = fieldValues[0];
            String price = fieldValues[1];
            String description = fieldValues[2];
            String quantity = fieldValues[3];
            String sImage = fieldValues[4];
            Product e = new Product(key, name, price, quantity,description, sImage);
            products.add(e);
        }
        db.close();
        adapter = new CustomProductAdapter(this, products);
        scrollView.setAdapter(adapter);
    }
}