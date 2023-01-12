package com.example.e_commerceapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Dashboard extends AppCompatActivity {
    EditText productNameEditText, priceEditText, descriptionEditText, qtyEditText;
    ImageView uploadPhoto;
    Button cancelBtn, saveBtn;
    int selectImgCode = 1;
    String sImage;
    int key = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        productNameEditText = findViewById(R.id.productNameEditText);
        priceEditText = findViewById(R.id.priceEditText);
        descriptionEditText = findViewById(R.id.descriptionEditText);
        qtyEditText = findViewById(R.id.qtyEditText);

        cancelBtn = findViewById(R.id.cancelBtn);
        saveBtn = findViewById(R.id.saveBtn);

        uploadPhoto = findViewById(R.id.uploadPhoto);

        cancelBtn.setOnClickListener(v -> finish());
        saveBtn.setOnClickListener(v -> saveFunc());
        uploadPhoto.setOnClickListener(v->imageUploader());



    }

    private void imageUploader() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Title"),selectImgCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1){
            Uri uri = data.getData();
            uploadPhoto.setImageURI(uri);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                ByteArrayOutputStream stream=new ByteArrayOutputStream();
                // compress Bitmap
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
                // Initialize byte array
                byte[] bytes=stream.toByteArray();
                // get base64 encoded string
                sImage= Base64.encodeToString(bytes,Base64.DEFAULT);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void saveFunc() {
        String name = productNameEditText.getText().toString();
        String price = priceEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        String quantity = qtyEditText.getText().toString();

        String specialCharactersString = "!@#$%&*()'+,-/:;<=>?[]^_`{|}";
        String numbers = "0123456789";
        if(name.length()<6){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage("Please enter full name!");

            builder.setTitle("NEED FULL NAME!");
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            return;
        }



        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);
            if (specialCharactersString.contains(Character.toString(ch)) || numbers.contains(Character.toString(ch))) {
//                String character = "Name contains special character try again";
//                Snackbar.make(findViewById(R.id.layout), character,
//                                Snackbar.LENGTH_SHORT)
//                        .show();
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setMessage("Name should only contain letters.");

                builder.setTitle("Incorrect name format!");
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return;
            }

        }



        System.out.println(key);

        String value = name + "___" + price + "___" + description + "___" + quantity + "___" + sImage + "___";
        System.out.println(value);
        KeyValueDB kdb = new KeyValueDB(this);
        kdb.insertKeyValue(String.valueOf(key),value);
        key++;


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Information Stored!");
        builder.setTitle("SUCCESS!");
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    }