package com.example.e_shop;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {
    Button login;
    EditText passwords, emails;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        login = findViewById(R.id.btn_login);
        emails = findViewById(R.id.et_email1);
        passwords = findViewById(R.id.et_password1);
        login.setOnClickListener(v-> login());
        firebaseDatabase = FirebaseDatabase.getInstance();

        reference = firebaseDatabase.getReference("Users");
    }
    public void login(){
        String phonedbs = emails.getText().toString().trim();
        String passdbs = passwords.getText().toString().trim();
        Query user = reference.orderByChild("phone").equalTo(phonedbs);

        reference.orderByChild("phone").equalTo(phonedbs).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    String passfDB = snapshot.child(phonedbs).child("password").getValue(String.class);
                    if(passfDB.equals(passdbs)){
                        Toast.makeText(getApplicationContext(), "Logged In Succesfully", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Invalid Password", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "No such User Exists", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG, error.getMessage());
            }
        });
    }
}