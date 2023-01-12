package com.example.e_commerceapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class CustomProductAdapter extends ArrayAdapter<Product> {
    private final Context context;
    private final ArrayList<Product> values;


    public CustomProductAdapter(@NonNull Context context, @NonNull ArrayList<Product> objects) {
        super(context, -1, objects);
        this.context = context;
        this.values = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View proView = inflater.inflate(R.layout.layout_product, parent, false);

        ImageView imgproduct = proView.findViewById(R.id.imgProduct);
        TextView nameproduct = proView.findViewById(R.id.nameproduct);
        TextView priceproduct = proView.findViewById(R.id.priceproduct);
        TextView descripproduct = proView.findViewById(R.id.descripproduct);
        byte[] decodedString = Base64.decode(values.get(position).sImage, Base64.URL_SAFE);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        imgproduct.setImageBitmap(decodedByte);
        nameproduct.setText(values.get(position).name);
        descripproduct.setText(values.get(position).description);

        return proView;
    }
}
