package com.example.e_commerceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class signup extends AppCompatActivity {
    Boolean firstCheckBoxCheck, secondCheckBoxCheck;
    TextView loginText;
    EditText userName, userId, password, phone, email, rePassword;
    Button goBtn, exitBtn;
    CheckBox firstCheckBox, secondCheckBox;
    TableRow phoneTableRow, userIdTableRow, passwordReTableRow, rememberUserIdRow;
    LinearLayout loginLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        loginText = findViewById(R.id.loginText);
        loginText.setOnClickListener(
                view -> {
                    HideRow(view);
                    userName.setText("");
                    email.setText("");
                    password.setText("");
                });

        loginLayout = findViewById(R.id.loginLayout);


        userName = findViewById(R.id.nameEditText);
        userId = findViewById(R.id.userIdEditText);
        password = findViewById(R.id.passwordEditText);
        phone = findViewById(R.id.phoneEditText);
        email = findViewById(R.id.emailEditText);
        rePassword = findViewById(R.id.passwordReEditText);


        firstCheckBox = findViewById(R.id.firstCheckBox);
        secondCheckBox = findViewById(R.id.secondCheckBox);

        phoneTableRow = findViewById(R.id.phoneTableRow);
        userIdTableRow = findViewById(R.id.userIdTableRow);
        passwordReTableRow = findViewById(R.id.passwordReTableRow);
        rememberUserIdRow = findViewById(R.id.rememberUserIdRow);

        goBtn = findViewById(R.id.goBtn);
        goBtn.setOnClickListener(v -> {

            SharedPreferences sp = this.getSharedPreferences("signupStore", MODE_PRIVATE);
            SharedPreferences.Editor e = sp.edit();
            String inputName = userName.getText().toString();
            String inputEmail = email.getText().toString();
            String inputPassword = password.getText().toString();

            if (inputName.equals(sp.getString("user_name", inputName)) && inputEmail.equals(sp.getString("email", inputEmail)) && inputPassword.equals(sp.getString("password", inputPassword))) {
                System.out.println("Login Successful");

            }

            if (password.getText().toString().equals(rePassword.getText().toString())) {
                e.putString("user_name", userName.getText().toString());
                e.putString("user_id", userId.getText().toString());
                e.putString("email", email.getText().toString());
                e.putString("phone", phone.getText().toString());
                e.putString("password", password.getText().toString());
                firstCheckBoxCheck = firstCheckBox.isChecked();
                secondCheckBoxCheck = secondCheckBox.isChecked();


                userName.setText("");
                email.setText("");
                password.setText("");
                rePassword.setText("");
                userId.setText("");

                e.putString("Remember_id", firstCheckBoxCheck.toString());
                e.putString("Remember_password", secondCheckBoxCheck.toString());
                e.apply();

                String name = sp.getString("user_name", "");
                String email = sp.getString("email", "");
                String phone = sp.getString("phone", "");
                String password = sp.getString("password", "");
                String rememberId = sp.getString("Remember_id", "");
                String rememberPassword = sp.getString("Remember_password", "");


                System.out.println("User Name:" + name);
                System.out.println("Email: " + email);
                System.out.println("Phone: " + phone);
                System.out.println("Password: " + password);
                System.out.println("Remember ID: " + rememberId);
                System.out.println("Remember Password" + rememberPassword);


//                String verify = String.valueOf(sp.getAll());
//                System.out.println(verify);

            }
//            else{
//                funcError();
//            }


        });

        exitBtn = findViewById(R.id.exitBtn);
        exitBtn.setOnClickListener(v -> finish());


    }

    private void HideRow(View view) {
        phoneTableRow.setVisibility(view.GONE);
        userIdTableRow.setVisibility(view.GONE);
        passwordReTableRow.setVisibility(view.GONE);
        rememberUserIdRow.setVisibility(view.GONE);
        loginLayout.setVisibility(view.GONE);
    }

    public void funcSave() {
//        System.out.println(userName.getText().toString().trim());

        if (firstCheckBox.isChecked()) {
            System.out.println("Remember User Id Checked");
        }

        if (secondCheckBox.isChecked()) {
            System.out.println("Remember Password Checked");
        }
    }

    public void funcError() {
        System.out.println("Re-type password doesn't match");
    }
}
