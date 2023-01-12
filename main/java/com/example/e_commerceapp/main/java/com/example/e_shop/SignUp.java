package com.example.e_shop;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class SignUp extends AppCompatActivity {
    Button register;
    EditText name, phone, email,password, rpass;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firebaseDatabase = FirebaseDatabase.getInstance();
        phone = findViewById(R.id.et_phone);
        name = findViewById(R.id.et_name);
        email = findViewById(R.id.et_email);
        password = findViewById(R.id.et_password);
        rpass = findViewById(R.id.et_repassword);
        register = findViewById(R.id.btn_register);

        register.setOnClickListener(v-> Register());
    }
    public void Register(){
        String namedb = name.getText().toString().trim();
        String phonedb = phone.getText().toString().trim();
        String emaildb = email.getText().toString().trim();
        String passworddb = password.getText().toString().trim();
        String rpassdb = rpass.getText().toString().trim();
        NewUser newUser = new NewUser(namedb, emaildb, phonedb, passworddb);

        databaseReference = firebaseDatabase.getReference("Users");
        //databaseReference.push().setValue(key);
        String key = phonedb;

        databaseReference.orderByChild("phone").equalTo(phonedb).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    Toast.makeText(getApplicationContext(), "User is already exists", Toast.LENGTH_LONG).show();
                } else {
                    databaseReference.child(key).setValue(newUser);
                    Toast.makeText(getApplicationContext(), "Registration is succesful", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG, error.getMessage());
            }
        });
}
}