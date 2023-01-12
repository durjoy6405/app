package com.example.e_commerceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;

import java.util.ArrayList;

public class homePage extends AppCompatActivity {
    ArrayList<Event> events;
    CustomEventAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        loadData();
    }

    private void loadData() {
        events = new ArrayList<>();
        KeyValueDB db = new KeyValueDB(this);
        Cursor rows = db.execute("SELECT * FROM key_value_pairs");
        if (rows.getCount() == 0) {
            return;
        }
        //events = new Event[rows.getCount()];
        while (rows.moveToNext()) {
            String key = rows.getString(0);
            String eventData = rows.getString(1);
            String[] fieldValues = eventData.split("___");


            String name = fieldValues[0];
            String price = fieldValues[1];
            String description = fieldValues[2];
            String sImage = fieldValues[3];

            byte[] bytes = Base64.decode(sImage,Base64.DEFAULT);
            Bitmap sImages = BitmapFactory.decodeByteArray(bytes,0, bytes.length);

            Event e = new Event(key, name, price, description, sImages);
            events.add(e);
        }
        db.close();
        adapter = new CustomEventAdapter(this, events);
    }
}